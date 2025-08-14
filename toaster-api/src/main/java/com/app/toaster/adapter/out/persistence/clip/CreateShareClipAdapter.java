package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.application.port.create_share_clip.out.CreateShareClipPort;
import com.app.toaster.clip.model.Clip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateShareClipAdapter implements CreateShareClipPort {
    private final ClipQueryRepository clipQueryRepository;

    @Override
    public Clip createShareClip(Long userId, String title) {
        // 우선순위는 공유클립 중 priority max값 +1
        ClipEntity shareClipEntity = clipQueryRepository.createClipWithMaxPriority(userId, title);
        return ClipMapper.toDomain(shareClipEntity);
    }
}
