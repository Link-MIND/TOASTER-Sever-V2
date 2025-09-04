package com.app.toaster.application.port.exit_share_clip.out;

public interface ExitShareClipPort {
    void exitShareClip(Long userId, Long clipId);

    void deleteByOwner(Long userId, Long clipId);
}
