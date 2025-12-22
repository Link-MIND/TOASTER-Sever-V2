package com.app.toaster.global.port;

import java.util.List;

public interface CheckToastOwnerPort {
	boolean existsByIdAndUserId(Long toastId, Long userId);

	boolean allOwnedByUser(Long userId, List<Long> toastIds);
}
