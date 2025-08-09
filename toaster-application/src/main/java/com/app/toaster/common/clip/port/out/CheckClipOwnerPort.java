package com.app.toaster.common.clip.port.out;

public interface CheckClipOwnerPort {
	boolean existsByIdAndUserId(Long clipId, Long userId);
}
