package com.app.toaster.application.port.create_share_clip.out;

import com.app.toaster.clip.model.Clip;

public interface CreateShareClipPort {

    Clip createShareClip(Long userId, String title);
}
