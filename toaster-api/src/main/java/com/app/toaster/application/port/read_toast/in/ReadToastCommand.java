package com.app.toaster.application.port.read_toast.in;

public record ReadToastCommand(
	Long userId, Long toastId, Boolean isRead
) {
	public static ReadToastCommand toCommand(Long userId, Long toastId, Boolean isRead){
		return new ReadToastCommand(userId, toastId, isRead);
	}
}
