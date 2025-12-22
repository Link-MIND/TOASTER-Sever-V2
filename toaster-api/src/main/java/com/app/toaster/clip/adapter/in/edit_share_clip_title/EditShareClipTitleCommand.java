package com.app.toaster.clip.adapter.in.edit_share_clip_title;

public record EditShareClipTitleCommand(
    Long userId,
    Long clipId,
    String clipTitle
) {
    public static EditShareClipTitleCommand toCommand(Long userId, Long clipId, EditShareClipRequestDto editShareClipRequestDto) {
        return new EditShareClipTitleCommand(userId, clipId, editShareClipRequestDto.title());

    }
}
