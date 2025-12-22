package com.app.toaster.clip.adapter.out.persistence.jpa;

import com.app.toaster.clip.domain.model.Clip;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ClipMapper {

    public Clip toDomain(Optional<ClipEntity> clipEntity) {
        return clipEntity.map(entity -> Clip.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .members(entity.getMembers())
            .type(entity.getType())
            .priority(entity.getPriority())
            .ownerId(entity.getOwnerId())
            .build()).orElse(null);
    }

    public Clip toDomain(ClipEntity clipEntity) {
        return Clip.builder()
            .id(clipEntity.getId())
            .title(clipEntity.getTitle())
            .members(clipEntity.getMembers())
            .type(clipEntity.getType())
            .priority(clipEntity.getPriority())
            .ownerId(clipEntity.getOwnerId())
            .build();
    }
}
