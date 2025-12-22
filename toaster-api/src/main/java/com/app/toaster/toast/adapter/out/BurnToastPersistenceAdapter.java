package com.app.toaster.toast.adapter.out;

import com.app.toaster.toast.domain.burned_toast.enums.NotificationStatus;
import com.app.toaster.toast.application.port.out.BurnToastPort;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BurnToastPersistenceAdapter implements BurnToastPort {
    private final BurnedToastRepository burnedToastRepository;

    @Override
    public void markAsBurned(List<Long> toastIds) {
        List<BurnedToastEntity> entities = toastIds.stream()
            .map(id -> BurnedToastEntity.builder()
                .burnedAt(LocalDate.now())
                .toastId(id)
                .notificationStatus(NotificationStatus.PENDING).build()).toList();
        burnedToastRepository.saveAll(entities);
    }
}