package com.app.toaster.application.port.load_recent_toast.out;

import com.app.toaster.clip.model.Clip;

public interface LoadClipPort {

	Clip loadClip(Long clipId);
}
