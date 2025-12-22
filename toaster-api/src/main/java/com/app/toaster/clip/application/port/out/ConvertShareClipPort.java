package com.app.toaster.clip.application.port.out;

import com.app.toaster.clip.domain.model.Clip;

public interface ConvertShareClipPort {
    Clip convertShareClipToPrivate(Long userId, Long clipId);
    Clip convertPrivateClipToShare(Long userId, Long clipId);
}
