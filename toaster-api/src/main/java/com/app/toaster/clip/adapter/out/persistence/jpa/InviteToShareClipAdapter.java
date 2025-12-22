package com.app.toaster.clip.adapter.out.persistence.jpa;

import com.app.toaster.clip.application.port.out.InviteToShareClipPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InviteToShareClipAdapter implements InviteToShareClipPort {

    private final ClipRepository clipRepository;

    @Override
    @Transactional
    public void addMember(Long clipId, Long userId) {
        ClipEntity clipEntity = clipRepository.findById(clipId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage()));
        clipEntity.addMember(userId);
    }
}
