package com.app.toaster.adapter.out.persistence.toast;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.toaster.application.port.CheckToastOwnerPort;
import com.app.toaster.application.port.create_toast.out.SaveToastPort;
import com.app.toaster.application.port.move_clip.out.UpdateToastClipPort;
import com.app.toaster.application.port.read_toast.out.UpdateToastPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.model.Toast;

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
		ToastEntity entity = toastRepository.findById(toastId)
			.orElseThrow(() -> new CustomException(Error.NOT_FOUND_TOAST_EXCEPTION,
				Error.NOT_FOUND_TOAST_EXCEPTION.getMessage()));

		entity.setReadStatus(isRead);
	}
}