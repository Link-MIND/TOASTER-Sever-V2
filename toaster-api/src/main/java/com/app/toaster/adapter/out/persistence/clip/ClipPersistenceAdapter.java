package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.application.port.common.CheckClipOwnerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClipPersistenceAdapter implements CheckClipOwnerPort {

    private final ClipRepository clipRepository;

    @Override
    public boolean existsByIdAndUserId(Long clipId, Long userId) {
        return clipRepository.existsByIdAndOwnerId(clipId, userId);
    }
}