package com.app.toaster.application.port.convert_share_clip.out;

import com.app.toaster.clip.model.Clip;

public interface ConvertShareClipPort {
    Clip convertShareClipToPrivate(Long userId, Long clipId);
    Clip convertPrivateClipToShare(Long userId, Long clipId);
}
