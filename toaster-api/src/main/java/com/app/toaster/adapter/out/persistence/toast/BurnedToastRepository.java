package com.app.toaster.adapter.out.persistence.toast;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BurnedToastRepository extends JpaRepository<BurnedToastEntity,Long> {
	void deleteByToastId(Long toastId);
}
