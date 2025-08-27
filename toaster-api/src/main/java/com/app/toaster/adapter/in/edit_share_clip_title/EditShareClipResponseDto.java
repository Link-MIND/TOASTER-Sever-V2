package com.app.toaster.adapter.in.edit_share_clip_title;

public record EditShareClipResponseDto(
    String result,
    Long clipId,
    String title
) {
    public static EditShareClipResponseDto success(Long clipId, String title){
        return new EditShareClipResponseDto("Y", clipId, title);
    }
    public static EditShareClipResponseDto fail(){
        return new EditShareClipResponseDto("N", null, null);
    }
}
