package com.app.toaster.clip.domain.model;

import com.app.toaster.toast.domain.toast.enums.ClipType;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Clip {

    private final Long id;
    private String title;
    private final Long ownerId;
    private int priority;
    private final ClipType type;
    private List<Long> members;

    @Builder
    public Clip(Long id, String title, Long ownerId, int priority, ClipType type, List<Long> members) {
        this.id = id;
        this.title = title;
        this.ownerId = ownerId;
        this.priority = priority;
        this.type = type;
        this.members = members;
    }

}
