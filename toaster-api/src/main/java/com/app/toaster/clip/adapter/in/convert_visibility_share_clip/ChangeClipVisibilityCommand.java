package com.app.toaster.clip.adapter.in.convert_visibility_share_clip;

public record ChangeClipVisibilityCommand(
    Long userId,
    Long clipId
) {
    public static ChangeClipVisibilityCommand toCommand(Long userId, Long clipId){
        return new ChangeClipVisibilityCommand(userId, clipId);
    }
}
