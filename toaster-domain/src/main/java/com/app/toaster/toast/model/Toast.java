package com.app.toaster.toast.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import lombok.Getter;

@Getter
public class Toast {

	private final Long id;
	private final Long userId;
	private Long clipId;
	private String title;
	private String linkUrl;
	private Boolean isRead;
	private String thumbnailUrl;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDate burnedAt;
	private boolean isTimerEnabled;

	public Toast(Long id, Long userId, Long clipId, String title, String linkUrl, Boolean isRead,
		String thumbnailUrl, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDate burnedAt, boolean isTimerEnabled) {
		this.id = id;
		this.userId = userId;
		this.clipId = clipId;
		this.title = title;
		this.linkUrl = linkUrl;
		this.isRead = isRead;
		this.thumbnailUrl = thumbnailUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.burnedAt = burnedAt;
		this.isTimerEnabled = isTimerEnabled;
	}

	public static Toast create(Long userId, Long clipId, String title, String linkUrl, String thumbnailUrl, LocalDate burnedAt, boolean isTimerEnabled) {
		return new Toast(null, userId, clipId, title, linkUrl, null, thumbnailUrl, null, null, burnedAt, isTimerEnabled);
	}
}
