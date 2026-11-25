package com.app.toaster.clip.adapter.out.persistence.jpa;

import com.app.toaster.clip.application.port.out.CheckShareClipOwnerPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckShareClipOwnerAdapter implements CheckShareClipOwnerPort {

    private final ClipRepository clipRepository;

    @Override
    public void check(Long clipId, Long userId) {
        if (!clipRepository.existsByIdAndOwnerId(clipId, userId)) {
            throw new ForbiddenException(Error.UNAUTHORIZED_ACCESS, Error.UNAUTHORIZED_ACCESS.getMessage());
        }
    }
}
