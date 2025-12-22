package com.app.toaster.clip.adapter.out.persistence.jpa;

import com.app.toaster.clip.application.port.out.EditShareClipTitlePort;
import com.app.toaster.clip.application.port.out.ExitShareClipPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EditShareClipAdapter implements EditShareClipTitlePort, ExitShareClipPort {

    private final ClipRepository clipRepository;
    @Override
    @Transactional
    public void editShareClipTitle(Long clipId, String title) {
        ClipEntity clipEntity = clipRepository.findById(clipId).orElseThrow(
            () -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage())
        );
        clipEntity.editTitle(title);
    }

    @Override
    @Transactional
    public void exitShareClip(Long userId, Long clipId) {
        ClipEntity clipEntity = clipRepository.findById(clipId).orElseThrow(
            () -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage())
        );
        clipEntity.exitMember(userId);
    }
}
