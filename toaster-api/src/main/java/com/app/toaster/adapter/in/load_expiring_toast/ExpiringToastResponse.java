package com.app.toaster.adapter.in.load_expiring_toast;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.toaster.toast.model.Toast;

public record ExpiringToastResponse(
	Long toastId,
	LocalDate burnedAt,
	String title,
	String domain,
	LocalDateTime createdAt
) {
	public static ExpiringToastResponse toResponse(Toast toast){
		return new ExpiringToastResponse(toast.getId(), toast.getBurnedAt(), toast.getTitle(), toast.getLinkUrl(),
			toast.getCreatedAt());
	}
}
