package com.app.toaster.application.port.exit_share_clip.in;

public record ExitShareClipResponseDto(
    String result,
    Long userId,
    Long clipId
) {
    public static ExitShareClipResponseDto success(Long userId, Long clipId){
        return new ExitShareClipResponseDto("Y", userId, clipId);
    }
    public static ExitShareClipResponseDto fail(Long userId, Long clipId){
        return new ExitShareClipResponseDto("N", userId, clipId);
    }
}
