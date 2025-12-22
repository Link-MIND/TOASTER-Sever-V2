package com.app.toaster.toast.adapter.in.edit_toast;

public record EditToastTitleRequest(
	Long toastId,
	String newTitle
) {
}
