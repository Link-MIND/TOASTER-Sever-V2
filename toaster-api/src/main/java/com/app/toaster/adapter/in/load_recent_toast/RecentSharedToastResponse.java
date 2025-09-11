package com.app.toaster.adapter.in.load_recent_toast;

import java.time.LocalDateTime;

import com.app.toaster.toast.model.Toast;

public record RecentSharedToastResponse(
	Long toastId,
	String title,
	String clipTitle,
	String domain,
	LocalDateTime createdAt,
	String savedBy
) {
	public static RecentSharedToastResponse toResponse(Toast toast, String clipTitle, String savedBy) {
		return new RecentSharedToastResponse(toast.getId(), toast.getTitle(), clipTitle, toast.getLinkUrl(),
			toast.getCreatedAt(), savedBy);
	}
}
