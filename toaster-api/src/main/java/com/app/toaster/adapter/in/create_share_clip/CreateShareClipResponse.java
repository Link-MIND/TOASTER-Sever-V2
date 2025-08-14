package com.app.toaster.adapter.in.create_share_clip;

import com.app.toaster.clip.model.Clip;

public record CreateShareClipResponse(
    String result,
    Clip clip
) {
    public static CreateShareClipResponse toResponse(Clip clip) {
        return new CreateShareClipResponse("Y", clip);
    }
}
