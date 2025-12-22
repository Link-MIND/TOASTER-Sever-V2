package com.app.toaster.clip.adapter.in.create_share_clip;

public record CreateShareClipCommand(
    Long userId,
    String title
) {
    public static CreateShareClipCommand toCommand(CreateShareClipRequest request){
        return new CreateShareClipCommand(request.userId(), request.title());
    }
}
