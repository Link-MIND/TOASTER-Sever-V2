package com.app.toaster.adapter.out.persistence.toast;

import com.app.toaster.application.port.create_toast.out.SaveToastPort;
import com.app.toaster.application.port.move_clip.out.UpdateToastClipPort;
import com.app.toaster.toast.model.Toast;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ToastPersistenceAdapter implements SaveToastPort, UpdateToastClipPort {

	private final ToastRepository toastRepository;
	private final ToastQueryRepository toastQueryRepository;

	@Override
	public void save(Toast toast) {
		ToastEntity entity = ToastMapper.toEntity(toast);
		toastRepository.save(entity);
	}

	@Override
	public void updateToastClip(List<Long> ids, Long clipId) {
		toastQueryRepository.bulkUpdateClipIdByIds(ids, clipId);
	}
}