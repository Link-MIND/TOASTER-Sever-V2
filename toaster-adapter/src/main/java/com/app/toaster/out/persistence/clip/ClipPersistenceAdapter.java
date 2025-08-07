package com.app.toaster.out.persistence.clip;

import org.springframework.stereotype.Component;

import com.app.toaster.toast.port.out.LoadClipPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClipPersistenceAdapter implements LoadClipPort {

    private final ClipRepository clipRepository;

    @Override
    public boolean existsByIdAndUserId(Long clipId, Long userId) {
        return clipRepository.existsByIdAndOwnerId(clipId, userId);
    }
}