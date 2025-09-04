package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.application.port.participate_clip.out.ParticipateShareClipPort;

public class ParticipateShareClipAdapter implements ParticipateShareClipPort {
    @Override
    public boolean participateClipMember(String socialId) {
        return false;
    }

    @Override
    public boolean migrateClipAndToasts(String socialId) {
        return false;
    }
}
