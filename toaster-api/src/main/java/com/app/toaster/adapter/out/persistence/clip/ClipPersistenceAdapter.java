package com.app.toaster.adapter.out.persistence.clip;

import java.util.Objects;

import com.app.toaster.application.port.common.CheckClipMemberPort;
import com.app.toaster.application.port.common.CheckClipOwnerPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.enums.ClipType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClipPersistenceAdapter implements CheckClipOwnerPort, CheckClipMemberPort {

    private final ClipRepository clipRepository;

    @Override
    public boolean checkClipPermission(Long clipId, Long userId) {
        ClipEntity clipEntity = clipRepository.findById(clipId).orElseThrow(
            () -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage())
        );

        // 소유자라면 허용
        if (Objects.equals(clipEntity.getOwnerId(), userId)) {
            return true;
        }

        // 소유자가 아닌경우
        return switch (clipEntity.getType()) {
            case PRIVATE -> false;
            case SHARED  -> checkUserInClipMember(clipId, userId);
        };
    }

    @Override
    public boolean existsByIdAndUserId(Long clipId, Long userId) {
        return clipRepository.existsByIdAndOwnerId(clipId, userId);
    }

    @Override
    public boolean checkUserInClipMember(Long userId, Long clipId) {
        ClipEntity clipEntity = clipRepository.findById(clipId).orElseThrow(
            () -> new CustomException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage())
        );
        return clipEntity.isUserInClipMembers(userId);
    }
}