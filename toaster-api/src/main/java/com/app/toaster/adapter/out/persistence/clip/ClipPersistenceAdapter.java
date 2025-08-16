package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.application.port.common.CheckClipMemberPort;
import com.app.toaster.application.port.common.CheckClipOwnerPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ClipPersistenceAdapter implements CheckClipOwnerPort, CheckClipMemberPort {

    private final ClipRepository clipRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIdAndUserId(Long clipId, Long userId) {
        return clipRepository.existsByIdAndOwnerId(clipId, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkUserInClipMember(Long userId, Long clipId) {
        ClipEntity clipEntity = clipRepository.findById(clipId).orElseThrow(
            () -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage())
        );
        return clipEntity.isUserInClipMembers(userId);
    }
}