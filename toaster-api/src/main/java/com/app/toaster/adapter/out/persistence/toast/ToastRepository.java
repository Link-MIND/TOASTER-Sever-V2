package com.app.toaster.adapter.out.persistence.toast;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToastRepository extends JpaRepository<ToastEntity, Long> {
	Boolean existsByIdAndUserId(Long toastId, Long userId);

	long countByIdInAndUserId(List<Long> toastIds, Long userId);

	List<ToastEntity> findByBurnedAtBeforeAndIsTimerEnabledTrueAndIsReadFalseAndIsBurnedFalse(LocalDate now);
}
