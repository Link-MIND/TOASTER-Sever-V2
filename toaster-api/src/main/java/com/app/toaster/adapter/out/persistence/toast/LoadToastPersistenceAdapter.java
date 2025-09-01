package com.app.toaster.adapter.out.persistence.toast;

import org.springframework.stereotype.Component;

import com.app.toaster.application.port.load_toast.out.LoadToastPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.model.Toast;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoadToastPersistenceAdapter implements LoadToastPort {

	private final ToastRepository toastRepository;

	@Override
	public Toast loadToast(Long toastId) {
		ToastEntity entity = toastRepository.findById(toastId)
			.orElseThrow(() -> new CustomException(Error.BAD_REQUEST_ID, Error.BAD_REQUEST_ID.getMessage()));

		return ToastMapper.toDomain(entity);
	}
}
