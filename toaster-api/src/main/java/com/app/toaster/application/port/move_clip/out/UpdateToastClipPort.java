package com.app.toaster.application.port.move_clip.out;

import java.util.List;

public interface UpdateToastClipPort {

	void updateToastClip(List<Long> ids, Long clipId);
}
