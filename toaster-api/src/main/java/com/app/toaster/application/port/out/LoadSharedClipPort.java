package com.app.toaster.application.port.out;

import com.app.toaster.clip.model.Clip;

public interface LoadSharedClipPort {
    Clip findClipById(Long clipId);
}
