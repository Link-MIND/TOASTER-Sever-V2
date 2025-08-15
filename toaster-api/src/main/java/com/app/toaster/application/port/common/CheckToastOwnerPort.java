package com.app.toaster.application.port.common;

import java.util.List;

public interface CheckToastOwnerPort {
	boolean existsByIdAndUserId(Long toastId, Long userId);

	boolean allOwnedByUser(Long userId, List<Long> toastIds);
}
