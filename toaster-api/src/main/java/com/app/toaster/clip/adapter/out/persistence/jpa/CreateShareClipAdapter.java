package com.app.toaster.clip.adapter.out.persistence.jpa;

import com.app.toaster.clip.application.port.out.CreateShareClipPort;
import com.app.toaster.clip.domain.model.Clip;
import com.app.toaster.toast.domain.toast.enums.ClipType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateShareClipAdapter implements CreateShareClipPort {
    private final ClipQueryRepository clipQueryRepository;
    private final ClipRepository clipJpaRepository;
    private final ClipMapper clipMapper;

    @Override
    public Clip createShareClip(Long userId, String title) {
        // 우선순위는 공유클립 중 priority max값 +1
        Integer maxPriority = clipQueryRepository.findMaxPriorityByOwnerId(userId);
        ClipEntity clipEntity = ClipEntity.builder()
            .ownerId(userId)
            .priority(maxPriority + 1)
            .title(title)
            .clipType(ClipType.SHARED)
            .build();
        ClipEntity clip = clipJpaRepository.save(clipEntity);
        return clipMapper.toDomain(clip);
    }
}
