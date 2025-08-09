package com.app.toaster.application.port;

public interface CheckClipOwnerPort {
	boolean existsByIdAndUserId(Long clipId, Long userId);
}
