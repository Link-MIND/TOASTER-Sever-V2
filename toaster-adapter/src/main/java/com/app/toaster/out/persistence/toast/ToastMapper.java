package com.app.toaster.out.persistence.toast;

import org.springframework.stereotype.Component;

import com.app.toaster.toast.model.Toast;

@Component
public class ToastMapper {

	public Toast toDomain(ToastEntity entity) {
		return new Toast(
			entity.getId(),
			entity.getUserId(),
			entity.getCategoryId(),
			entity.getTitle(),
			entity.getLinkUrl(),
			entity.getIsRead(),
			entity.getThumbnailUrl(),
			entity.getCreatedAt(),
			entity.getUpdateAt()
		);
	}

	public ToastEntity toEntity(Toast domain, Long userId, Long categoryId) {
		return ToastEntity.builder()
			.userId(userId)
			.categoryId(categoryId)
			.title(domain.getTitle())
			.linkUrl(domain.getLinkUrl())
			.thumbnailUrl(domain.getThumbnailUrl())
			.build();
	}
}

