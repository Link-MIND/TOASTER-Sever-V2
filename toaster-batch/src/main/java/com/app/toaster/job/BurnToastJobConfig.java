package com.app.toaster.job;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.app.toaster.adapter.in.burn_toast.BurnToastPort;
import com.app.toaster.application.port.read_toast.out.UpdateToastPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BurnToastJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager tx;
    private final BurnToastPort burnToastPort;
    private final UpdateToastPort updateToastPort;


    @Bean
    public Job burnToastJob(Step burnToastStep) {
        return new JobBuilder("burnToastJob", jobRepository)
            .start(burnToastStep)
            .validator(params -> {
                String d = params.getString("targetDate");
                if (d == null) throw new JobParametersInvalidException("targetDate required (yyyy-MM-dd)");
                LocalDate.parse(d);
            })
            .build();
    }


    @Bean
    public Step burnToastStep(ItemReader<Long> expiredToastIdReader,
        ItemWriter<Long> burnToastWriter) {
        return new StepBuilder("burnToastStep", jobRepository)
            .<Long, Long>chunk(100, tx)
            .reader(expiredToastIdReader)
            .processor(i -> i)
            .writer(burnToastWriter)
            .faultTolerant().retryLimit(3).retry(Exception.class)
            .skipLimit(100).skip(Exception.class)
            .build();
    }


    @Bean
    @StepScope
    public ListItemReader<Long> expiredToastIdReader( @Value("#{jobParameters['targetDate']}") String targetDate) {
        LocalDate date = LocalDate.parse(targetDate, DateTimeFormatter.ISO_LOCAL_DATE);
        List<Long> expired = burnToastPort.findExpiredToasts(date);
        return new ListItemReader<>(expired);
    }

    @Bean
    @StepScope
    public ItemWriter<Long> burnToastWriter() {
        // Writer 1: 타버린링크 테이블 업데이트
        ItemWriter<Long> burnedToastWriter = chunk -> {
            List<? extends Long> raw = chunk.getItems();
            if (raw.isEmpty()) return;

            List<Long> ids = new java.util.ArrayList<>(raw);
            burnToastPort.markAsBurned(ids);
        };

        // Writer 2: toaster isBurned 갱신
        ItemWriter<Long> linkFlagWriter = chunk -> {
            List<? extends Long> raw = chunk.getItems();
            if (raw.isEmpty()) return;

            List<Long> ids = new java.util.ArrayList<>(raw);
            updateToastPort.updateToastBurn(ids);
        };


        CompositeItemWriter<Long> composite = new CompositeItemWriter<>();
        composite.setDelegates(java.util.List.of(burnedToastWriter, linkFlagWriter));
        return composite;
    }
}