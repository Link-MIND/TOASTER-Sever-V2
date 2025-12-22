package com.app.toaster.clip.adapter.out.persistence.jpa;

import com.app.toaster.clip.application.port.out.ConvertShareClipPort;
import com.app.toaster.clip.domain.model.Clip;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ConvertShareClipAdapter implements ConvertShareClipPort {
    private final ClipRepository clipRepository;
    private final ClipMapper clipMapper;
    @Override
    public Clip convertShareClipToPrivate(Long userId, Long clipId) {
        ClipEntity clipEntity = clipRepository.findById(clipId)
            .orElseThrow(() -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage()));
        clipEntity.changePrivate();
        return clipMapper.toDomain(clipEntity);
    }

    @Override
    public Clip convertPrivateClipToShare(Long userId, Long clipId) {
        ClipEntity clipEntity = clipRepository.findById(clipId)
            .orElseThrow(() -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage()));
        clipEntity.changeShared();
        return clipMapper.toDomain(clipEntity);
    }
}
