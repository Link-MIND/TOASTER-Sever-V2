package com.app.toaster.toast.port.out;

public interface LoadClipPort {
	boolean existsByIdAndUserId(Long clipId, Long userId);
}
