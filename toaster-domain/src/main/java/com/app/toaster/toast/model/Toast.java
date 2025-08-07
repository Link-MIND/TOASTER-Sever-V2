package com.app.toaster.toast.model;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Toast {

	private final Long id;
	private final Long userId;
	private Long categoryId;
	private String title;
	private String linkUrl;
	private Boolean isRead;
	private String thumbnailUrl;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Toast(Long id, Long userId, Long categoryId, String title, String linkUrl, Boolean isRead,
		String thumbnailUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.userId = userId;
		this.categoryId = categoryId;
		this.title = title;
		this.linkUrl = linkUrl;
		this.isRead = isRead;
		this.thumbnailUrl = thumbnailUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public void updateTitle(String title) {
		this.title = title;
	}

	public void updateIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public void updateCategory(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void updateThumbnail(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public void setUpdatedNow() {
		this.updatedAt = LocalDateTime.now();
	}

	public boolean isOwner(Long requestUserId) {
		return this.userId.equals(requestUserId);
	}
}
