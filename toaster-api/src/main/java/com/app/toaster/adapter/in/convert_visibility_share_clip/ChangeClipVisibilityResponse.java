package com.app.toaster.adapter.in.convert_visibility_share_clip;

import com.app.toaster.clip.model.Clip;

public record ChangeClipVisibilityResponse(
    String result,
    String afterState
) {
    public static ChangeClipVisibilityResponse toResponse(Clip clip){
        return new ChangeClipVisibilityResponse("Y", clip.getType().name());
    }
}
