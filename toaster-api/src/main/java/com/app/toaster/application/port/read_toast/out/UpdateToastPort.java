package com.app.toaster.application.port.read_toast.out;

public interface UpdateToastPort {

	void changeReadStatus(Long toastId, Boolean isRead);

	void editToastTitle(Long toastId, String newTitle);

	void restoreToast(Long aLong);
}
