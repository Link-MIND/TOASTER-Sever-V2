package com.app.toaster.toast.port.in;

public interface CreateToastUseCase {

	public void createToast(Long userId, String linkUrl, Long clipId, boolean isTimerEnabled);
}
