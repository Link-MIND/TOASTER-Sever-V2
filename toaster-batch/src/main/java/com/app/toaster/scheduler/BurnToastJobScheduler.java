package com.app.toaster.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class BurnToastJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job burnToastJob;

    // 매일 밤 12시
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void run() throws Exception {
        log.info("[{}] 스케줄러 시작", LocalDateTime.now());

        LocalDate target = LocalDate.now(java.time.ZoneId.of("Asia/Seoul"));
        JobParameters params = new JobParametersBuilder()
            .addString("targetDate", target.format(DateTimeFormatter.ISO_LOCAL_DATE))
            .addLong("run.id", System.currentTimeMillis())
            .toJobParameters();

        jobLauncher.run(burnToastJob, params);
    }
}