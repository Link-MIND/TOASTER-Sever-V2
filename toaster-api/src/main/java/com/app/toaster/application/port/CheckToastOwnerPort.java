package com.app.toaster.application.port;

public interface CheckToastOwnerPort {
	boolean existsByIdAndUserId(Long toastId, Long userId);
}
