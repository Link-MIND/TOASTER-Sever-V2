package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.clip.model.Clip;

public class ClipMapper {

    public static Clip toDomain(ClipEntity clipEntity){
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
