package com.app.toaster.out.persistence.toast;

import org.springframework.stereotype.Component;

import com.app.toaster.toast.model.Toast;
import com.app.toaster.toast.port.out.SaveToastPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ToastPersistenceAdapter implements SaveToastPort {

	private final ToastRepository toastRepository;

	@Override
	public void save(Toast toast) {
		ToastEntity entity = ToastMapper.toEntity(toast);
		toastRepository.save(entity);
	}
}