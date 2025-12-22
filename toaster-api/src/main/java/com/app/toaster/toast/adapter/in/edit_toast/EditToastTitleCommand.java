package com.app.toaster.toast.adapter.in.edit_toast;

public record EditToastTitleCommand(
	Long userId,
	Long toastId,
	String newTitle
) {
	public static EditToastTitleCommand toCommand(Long userId, Long toastId, String newTitle){
		return new EditToastTitleCommand(userId, toastId, newTitle);
	}
}
