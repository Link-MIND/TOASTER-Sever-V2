package com.app.toaster.toast.application.port.in;

import com.app.toaster.clip.adapter.in.move_clip.MoveClipCommand;

public interface MoveClipUseCase {

	public void moveClip(MoveClipCommand command);
}
