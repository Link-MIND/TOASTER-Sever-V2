package com.app.toaster.out.persistence.toast;

import java.time.LocalDateTime;

import com.app.toaster.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "toast")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class ToastEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private Long clipId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String linkUrl;

	private Boolean isRead;

	@Column(columnDefinition = "TEXT")
	private String thumbnailUrl;

	private LocalDateTime burnedAt;

	private Boolean isTimerEnabled;

	@Builder
	public ToastEntity(Long userId, Long clipId, String title, String linkUrl, String thumbnailUrl, LocalDateTime burnedAt, boolean isTimerEnabled) {
		this.userId = userId;
		this.clipId = clipId;
		this.title = title;
		this.linkUrl = linkUrl;
		this.isRead = false;
		this.isTimerEnabled = isTimerEnabled;
		this.burnedAt = burnedAt;
		this.thumbnailUrl = thumbnailUrl;
	}
}
