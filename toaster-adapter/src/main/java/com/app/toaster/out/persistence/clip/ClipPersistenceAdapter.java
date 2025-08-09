package com.app.toaster.out.persistence.clip;

import org.springframework.stereotype.Component;

import com.app.toaster.common.clip.port.out.CheckClipOwnerPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClipPersistenceAdapter implements CheckClipOwnerPort {

    private final ClipRepository clipRepository;

    @Override
    public boolean existsByIdAndUserId(Long clipId, Long userId) {
        return clipRepository.existsByIdAndOwnerId(clipId, userId);
    }
}