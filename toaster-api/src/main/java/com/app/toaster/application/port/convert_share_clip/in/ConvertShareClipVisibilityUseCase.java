package com.app.toaster.application.port.convert_share_clip.in;

import com.app.toaster.adapter.in.convert_visibility_share_clip.ChangeClipVisibilityResponse;

public interface ConvertShareClipVisibilityUseCase {
    ChangeClipVisibilityResponse changePrivateClipToShareClip(ChangeClipVisibilityCommand command);
    ChangeClipVisibilityResponse changeShareClipToPrivateClip(ChangeClipVisibilityCommand command);
}
