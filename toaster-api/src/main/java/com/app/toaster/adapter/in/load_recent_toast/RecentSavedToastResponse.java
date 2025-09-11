package com.app.toaster.adapter.in.load_recent_toast;

import java.time.LocalDateTime;

import com.app.toaster.toast.model.Toast;

public record RecentSavedToastResponse(
	Long toastId,
	String title,
	String clipTitle,
	String domain,
	LocalDateTime createdAt
) {
	public static RecentSavedToastResponse toResponse(Toast toast, String clipTitle){
		return new RecentSavedToastResponse(toast.getId(), toast.getTitle(), clipTitle, toast.getLinkUrl(),
			toast.getCreatedAt());
	}
}
