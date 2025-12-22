package com.app.toaster.clip.application.port.in;

import com.app.toaster.clip.adapter.in.convert_visibility_share_clip.ChangeClipVisibilityCommand;
import com.app.toaster.clip.adapter.in.convert_visibility_share_clip.ChangeClipVisibilityResponse;

public interface ConvertShareClipVisibilityUseCase {
    ChangeClipVisibilityResponse changePrivateClipToShareClip(ChangeClipVisibilityCommand command);
    ChangeClipVisibilityResponse changeShareClipToPrivateClip(ChangeClipVisibilityCommand command);
}
