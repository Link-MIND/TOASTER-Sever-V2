package com.app.toaster.toast.adapter.in.read_toast;

public interface ReadToastUseCase {

	void readToast(Long userId, Long toastId, Boolean isRead);
}
