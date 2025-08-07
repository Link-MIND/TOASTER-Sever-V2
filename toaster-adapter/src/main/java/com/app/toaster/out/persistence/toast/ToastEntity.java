package com.app.toaster.out.persistence.toast;

import com.app.toaster.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private Long categoryId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String linkUrl;

	private Boolean isRead;

	@Column(columnDefinition = "TEXT")
	private String thumbnailUrl;

	@Builder
	public ToastEntity(Long userId, Long categoryId, String title, String linkUrl, String thumbnailUrl) {
		this.userId = userId;
		this.categoryId = categoryId;
		this.title = title;
		this.linkUrl = linkUrl;
		this.isRead = false;
		this.thumbnailUrl = thumbnailUrl;
	}
}
