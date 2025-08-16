package com.app.toaster.adapter.in.exit_share_clip_member;

public record ExitShareClipCommand(
    Long userId,
    Long clipId
) {
    public static ExitShareClipCommand of(Long userId, Long clipId){
        return new ExitShareClipCommand(userId, clipId);
    }
}
