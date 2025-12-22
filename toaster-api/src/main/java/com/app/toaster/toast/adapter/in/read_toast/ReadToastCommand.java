package com.app.toaster.toast.adapter.in.read_toast;

public record ReadToastCommand(
	Long userId, Long toastId, Boolean isRead
) {
}
