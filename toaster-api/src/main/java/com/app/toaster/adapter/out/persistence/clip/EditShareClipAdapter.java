package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.application.port.edit_share_clip_title.out.EditShareClipTitlePort;
import com.app.toaster.application.port.exit_share_clip.out.ExitShareClipPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.BadRequestException;
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
        ClipEntity clipEntity = findByClipId(clipId);
        clipEntity.editTitle(title);
    }

    @Override
    @Transactional
    public void exitShareClip(Long userId, Long clipId) {
        ClipEntity clipEntity = findByClipId(clipId);
        if (clipEntity.isUserInClipMembers(userId)) {
            clipEntity.exitMember(userId);
        } else {
            throw new BadRequestException(Error.UNAUTHORIZED_ACCESS, Error.UNAUTHORIZED_ACCESS.getMessage());
        }
    }

    @Override
    public void deleteByOwner(Long userId, Long clipId) {
        ClipEntity clipEntity = findByClipId(clipId);
        clipEntity.softDelete(userId);
    }

    private ClipEntity findByClipId(Long clipId) {
        return clipRepository.findById(clipId).orElseThrow(
            () -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage())
        );
    }
}
