package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.application.port.edit_share_clip_title.out.EditShareClipTitlePort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EditShareClipAdapter implements EditShareClipTitlePort {

    private final ClipRepository clipRepository;
    @Override
    @Transactional
    public void editShareClipTitle(Long clipId, String title) {
        ClipEntity clipEntity = clipRepository.findById(clipId).orElseThrow(
            () -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage())
        );
        clipEntity.editTitle(title);
    }
}
