package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.application.port.convert_share_clip.out.ConvertShareClipPort;
import com.app.toaster.clip.model.Clip;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ConvertShareClipAdapter implements ConvertShareClipPort {
    private final ClipRepository clipRepository;
    @Override
    public Clip convertShareClipToPrivate(Long userId, Long clipId) {
        ClipEntity clipEntity = clipRepository.findById(clipId)
            .orElseThrow(() -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage()));
        clipEntity.changePrivate();
        return ClipMapper.toDomain(clipEntity);
    }

    @Override
    public Clip convertPrivateClipToShare(Long userId, Long clipId) {
        ClipEntity clipEntity = clipRepository.findById(clipId)
            .orElseThrow(() -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage()));
        clipEntity.changeShared();
        return ClipMapper.toDomain(clipEntity);
    }
}
