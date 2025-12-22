package com.app.toaster.clip.application.port.in;

public interface InviteToShareClipUseCase {
    void invite(Long userId, Long clipId, Long inviteeId);
}
