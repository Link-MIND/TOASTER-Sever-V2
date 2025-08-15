package com.app.toaster.adapter.in.edit_toast_title;

public record EditToastTitleRequest(
	Long toastId,
	String newTitle
) {
}
