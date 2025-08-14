package com.app.toaster.application.port.create_share_clip.in;

import com.app.toaster.adapter.in.create_share_clip.CreateShareClipRequest;

public record CreateShareClipCommand(
    Long userId,
    String title
) {
    public static CreateShareClipCommand toCommand(Long userId, CreateShareClipRequest request){
        return new CreateShareClipCommand(userId, request.title());
    }
}
