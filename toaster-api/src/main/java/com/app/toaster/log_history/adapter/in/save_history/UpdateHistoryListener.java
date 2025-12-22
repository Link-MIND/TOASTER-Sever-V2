package com.app.toaster.log_history.adapter.in.save_history;

import com.app.toaster.log_history.adapter.out.UpdateHistory;
import com.app.toaster.log_history.adapter.out.UpdateHistoryRepository;
import com.app.toaster.trace.model.UpdateHistoryEvent;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateHistoryListener {

    private final UpdateHistoryRepository updateHistoryRepository;

    @Async
    @EventListener
    @Transactional
    public void saveUpdateHistory(UpdateHistoryEvent event) {
        log.info("hi");
        updateHistoryRepository.save(
            UpdateHistory.builder()
                .eventString(event.eventString())
                .domain(event.domain())
                .domainId(event.domainId())
                .userId(event.userId())
                .eventDateTime(LocalDateTime.now())
                .build()
        );
        log.info("저장" +event.eventString()+event.domain()+event.domainId()+event.userId());
    }
}
