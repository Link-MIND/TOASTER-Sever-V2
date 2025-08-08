package com.app.toaster.out.persistence.toast;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToastRepository extends JpaRepository<ToastEntity, Long> {
}
