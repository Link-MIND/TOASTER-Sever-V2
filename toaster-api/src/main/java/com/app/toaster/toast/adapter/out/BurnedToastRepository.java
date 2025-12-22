package com.app.toaster.toast.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BurnedToastRepository extends JpaRepository<BurnedToastEntity,Long> {
	void deleteByToastId(Long toastId);
}
