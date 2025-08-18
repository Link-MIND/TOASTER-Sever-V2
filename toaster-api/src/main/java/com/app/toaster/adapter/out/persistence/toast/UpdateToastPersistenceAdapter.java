package com.app.toaster.adapter.out.persistence.toast;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.toaster.application.port.move_clip.out.UpdateToastClipPort;
import com.app.toaster.application.port.read_toast.out.UpdateToastPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateToastPersistenceAdapter implements UpdateToastClipPort, UpdateToastPort {

	private final ToastRepository toastRepository;
	private final ToastQueryRepository toastQueryRepository;

	@Override
	public void updateToastClip(List<Long> ids, Long clipId) {
		toastQueryRepository.bulkUpdateClipIdByIds(ids, clipId);
	}

	@Override
	public void changeReadStatus(Long toastId, Boolean isRead) {
		ToastEntity entity = getToastEntity(toastId);
		entity.setReadStatus(isRead);
	}

	@Override
	public void editToastTitle(Long toastId, String newTitle) {
		ToastEntity entity = getToastEntity(toastId);
		entity.setTitle(newTitle);
	}

	@Override
	public void restoreToast(Long toastId) {
		ToastEntity entity = getToastEntity(toastId);
		entity.store();
	}

	@Override
	public void updateToastBurn(List<Long> ids) {
		List<ToastEntity> entities = toastRepository.findAllById(ids);
		entities.forEach(ToastEntity::burnToast);
		toastRepository.saveAll(entities);
	}

	private ToastEntity getToastEntity(Long toastId){
		return toastRepository.findById(toastId)
			.orElseThrow(() -> new CustomException(Error.NOT_FOUND_TOAST_EXCEPTION,
				Error.NOT_FOUND_TOAST_EXCEPTION.getMessage()));
	}
}