package com.app.toaster.toast.adapter.out;

import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
		toastRepository.save(entity);
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
	}

	private ToastEntity getToastEntity(Long toastId){
		return toastRepository.findById(toastId)
			.orElseThrow(() -> new CustomException(Error.NOT_FOUND_TOAST_EXCEPTION,
				Error.NOT_FOUND_TOAST_EXCEPTION.getMessage()));
	}
}