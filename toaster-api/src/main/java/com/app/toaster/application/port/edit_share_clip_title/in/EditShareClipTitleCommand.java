package com.app.toaster.application.port.edit_share_clip_title.in;

import com.app.toaster.adapter.in.edit_share_clip_title.EditShareClipRequestDto;

public record EditShareClipTitleCommand(
    Long userId,
    Long clipId,
    String clipTitle
) {
    public static EditShareClipTitleCommand toCommand(Long userId, Long clipId, EditShareClipRequestDto editShareClipRequestDto) {
        return new EditShareClipTitleCommand(userId, clipId, editShareClipRequestDto.title());

    }
}
