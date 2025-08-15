package com.app.toaster.application.port.common;

public interface CheckToastOwnerPort {
	boolean existsByIdAndUserId(Long toastId, Long userId);
}
