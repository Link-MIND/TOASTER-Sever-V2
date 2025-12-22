package com.app.toaster.clip.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClipRepository extends JpaRepository<ClipEntity,Long> {
	boolean existsByIdAndOwnerId(Long clipId, Long ownerId);
}
