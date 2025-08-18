package com.app.toaster.adapter.out.persistence.toast;

import com.app.toaster.toast.model.Toast;
import org.springframework.stereotype.Component;

@Component
public class ToastMapper {

	public static Toast toDomain(ToastEntity entity) {
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
			entity.getIsBurned(),
			entity.getIsTimerEnabled()
		);
	}

	public static ToastEntity toEntity(Toast domain) {
		return ToastEntity.builder()
			.userId(domain.getUserId())
			.clipId(domain.getClipId())
			.title(domain.getTitle())
			.linkUrl(domain.getLinkUrl())
			.thumbnailUrl(domain.getThumbnailUrl())
			.burnedAt(domain.getBurnedAt())
			.isBurned(domain.isBurned())
			.isTimerEnabled(domain.isTimerEnabled())
			.build();
	}
}

