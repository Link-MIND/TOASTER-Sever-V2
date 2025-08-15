package com.app.toaster.burned_toast.model;

import java.time.LocalDate;

import com.app.toaster.burned_toast.enums.NotificationStatus;

import lombok.Getter;

@Getter
public class BurnedToast {
	private final Long id;
	private LocalDate burnedAt;
	private final Long toastId;
	private NotificationStatus notificationStatus;

	public BurnedToast(Long id, LocalDate burnedAt, Long toastId, NotificationStatus notificationStatus) {
		this.id = id;
		this.burnedAt = burnedAt;
		this.toastId = toastId;
		this.notificationStatus = notificationStatus;
	}
}
