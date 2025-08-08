package com.app.toaster.out.persistence.toast;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.toaster.move_clip.port.out.UpdateToastClipPort;
import com.app.toaster.toast.model.Toast;
import com.app.toaster.toast.port.out.SaveToastPort;

import lombok.RequiredArgsConstructor;

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