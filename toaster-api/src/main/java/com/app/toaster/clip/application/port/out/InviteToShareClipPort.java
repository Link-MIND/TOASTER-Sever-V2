package com.app.toaster.clip.application.port.out;

public interface InviteToShareClipPort {
    void addMember(Long clipId, Long userId);
}
