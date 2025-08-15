package com.app.toaster.application.port.edit_toast_title.in;

public record EditToastTitleCommand(
	Long userId,
	Long toastId,
	String newTitle
) {
	public static EditToastTitleCommand toCommand(Long userId, Long toastId, String newTitle){
		return new EditToastTitleCommand(userId, toastId, newTitle);
	}
}
