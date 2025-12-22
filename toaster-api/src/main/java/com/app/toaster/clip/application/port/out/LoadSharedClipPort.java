package com.app.toaster.clip.application.port.out;

import com.app.toaster.clip.domain.model.Clip;

public interface LoadSharedClipPort {
    Clip findClipById(Long clipId);
}
