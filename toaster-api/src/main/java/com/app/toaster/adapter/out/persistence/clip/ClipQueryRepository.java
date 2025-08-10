package com.app.toaster.adapter.out.persistence.clip;

public interface ClipQueryRepository {
    Integer findMaxPriorityByOwnerId(Long ownerId);
    ClipEntity createClipWithMaxPriority(Long userId, String title);
}
