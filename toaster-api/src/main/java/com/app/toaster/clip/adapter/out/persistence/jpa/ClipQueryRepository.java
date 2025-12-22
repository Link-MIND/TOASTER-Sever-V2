package com.app.toaster.clip.adapter.out.persistence.jpa;

public interface ClipQueryRepository {
    Integer findMaxPriorityByOwnerId(Long ownerId);
}
