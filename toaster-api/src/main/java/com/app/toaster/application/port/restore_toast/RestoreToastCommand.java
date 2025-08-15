package com.app.toaster.application.port.restore_toast;

public record RestoreToastCommand(Long userId, Long toastId) {
	public static RestoreToastCommand toCommand(Long userId, Long toastId){
		return new RestoreToastCommand(userId, toastId);
	}
}
