package com.app.toaster.out.persistence.toast;

import org.springframework.stereotype.Component;

import com.app.toaster.toast.model.Toast;
import com.app.toaster.toast.port.out.SaveToastPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ToastPersistenceAdapter implements SaveToastPort {

	private final ToastRepository toastRepository;
	private final ToastMapper toastMapper;

	@Override
	public void save(Toast toast) {
		ToastEntity entity = toastMapper.toEntity(toast);
		toastRepository.save(entity);
	}
}