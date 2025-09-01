package com.app.toaster.application.port.load_toast.out;

import com.app.toaster.toast.model.Toast;

public interface LoadToastPort {
	Toast loadToast(Long toastId);
}
