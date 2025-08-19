package com.app.toaster.adapter.out.persistence.toast;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.app.toaster.adapter.in.burn_toast.BurnToastPort;
import com.app.toaster.adapter.in.burn_toast.ExpiredToastPort;
import com.app.toaster.burned_toast.enums.NotificationStatus;
import com.app.toaster.toast.model.Toast;

import lombok.RequiredArgsConstructor;

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