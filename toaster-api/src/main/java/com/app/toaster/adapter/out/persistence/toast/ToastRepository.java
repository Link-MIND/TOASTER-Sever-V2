package com.app.toaster.adapter.out.persistence.toast;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToastRepository extends JpaRepository<ToastEntity, Long> {
	Boolean existsByIdAndUserId(Long toastId, Long userId);
}
