package com.app.toaster.application.port.participate_clip.out;

public interface ParticipateShareClipPort {
    boolean participateClipMember(String socialId);
    boolean migrateClipAndToasts(String socialId);
}
