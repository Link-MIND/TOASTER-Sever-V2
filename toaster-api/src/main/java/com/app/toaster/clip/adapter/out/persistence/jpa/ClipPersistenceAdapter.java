package com.app.toaster.clip.adapter.out.persistence.jpa;

import com.app.toaster.global.port.CheckClipMemberPort;
import com.app.toaster.global.port.CheckClipOwnerPort;
import com.app.toaster.clip.application.port.out.LoadSharedClipPort;
import com.app.toaster.clip.domain.model.Clip;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClipPersistenceAdapter implements CheckClipOwnerPort, CheckClipMemberPort,
    LoadSharedClipPort {

    private final ClipRepository clipRepository;
    private final ClipMapper clipMapper;

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

    @Override
    public Clip findClipById(Long clipId) {
        Optional<ClipEntity> clipEntity = clipRepository.findById(clipId);
        return clipMapper.toDomain(clipEntity);
    }
}