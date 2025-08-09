package com.app.toaster.move_clip.port.out;

import java.util.List;

public interface UpdateToastClipPort {

	void updateToastClip(List<Long> ids, Long clipId);
}
