package com.app.toaster.application.port.read_toast.out;

import java.util.List;

public interface UpdateToastPort {

	void changeReadStatus(Long toastId, Boolean isRead);

	void editToastTitle(Long toastId, String newTitle);

	void restoreToast(Long toastId);

	void updateToastBurn(List<Long> ids);
}
