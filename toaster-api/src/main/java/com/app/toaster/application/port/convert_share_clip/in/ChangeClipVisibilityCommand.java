package com.app.toaster.application.port.convert_share_clip.in;

public record ChangeClipVisibilityCommand(
    Long userId,
    Long clipId
) {
    public static ChangeClipVisibilityCommand toCommand(Long userId, Long clipId){
        return new ChangeClipVisibilityCommand(userId, clipId);
    }
}
