package com.app.toaster.out.persistence.clip;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClipRepository extends JpaRepository<ClipEntity,Long> {
	boolean existsByIdAndOwnerId(Long clipId, Long ownerId);
}
