package com.app.toaster.application.port.common;

public interface CheckClipOwnerPort {
	boolean existsByIdAndUserId(Long clipId, Long userId);
}
