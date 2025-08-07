package com.app.toaster.out.persistence.toast;

import org.springframework.stereotype.Component;

import com.app.toaster.toast.model.Toast;

@Component
public class ToastMapper {

	public Toast toDomain(ToastEntity entity) {
		return new Toast(
			entity.getId(),
			entity.getUserId(),
			entity.getClipId(),
			entity.getTitle(),
			entity.getLinkUrl(),
			entity.getIsRead(),
			entity.getThumbnailUrl(),
			entity.getCreatedAt(),
			entity.getUpdateAt(),
			entity.getBurnedAt(),
			entity.getIsTimerEnabled()
		);
	}

	public ToastEntity toEntity(Toast domain) {
		return ToastEntity.builder()
			.userId(domain.getUserId())
			.clipId(domain.getClipId())
			.title(domain.getTitle())
			.linkUrl(domain.getLinkUrl())
			.thumbnailUrl(domain.getThumbnailUrl())
			.burnedAt(domain.getBurnedAt())
			.isTimerEnabled(domain.isTimerEnabled())
			.build();
	}
}

