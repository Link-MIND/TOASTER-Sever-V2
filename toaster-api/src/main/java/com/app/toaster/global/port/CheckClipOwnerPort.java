package com.app.toaster.global.port;

public interface CheckClipOwnerPort {
	boolean existsByIdAndUserId(Long clipId, Long userId);

	boolean checkClipPermission(Long clipId, Long userId);

}
