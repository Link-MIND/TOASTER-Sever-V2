package com.app.toaster.clip.application.port.out;

import com.app.toaster.clip.domain.model.Clip;

public interface CreateShareClipPort {

    Clip createShareClip(Long userId, String title);
}
