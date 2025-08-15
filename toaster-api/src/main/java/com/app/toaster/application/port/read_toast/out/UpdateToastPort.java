package com.app.toaster.application.port.read_toast.out;

public interface UpdateToastPort {

	public void changeReadStatus(Long toastId, Boolean isRead);
}
