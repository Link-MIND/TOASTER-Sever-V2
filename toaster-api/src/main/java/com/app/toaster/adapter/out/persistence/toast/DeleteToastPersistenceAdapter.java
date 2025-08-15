package com.app.toaster.adapter.out.persistence.toast;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.toaster.application.port.delete_toast.out.DeleteToastPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteToastPersistenceAdapter implements DeleteToastPort {
	private final ToastRepository toastRepository;
	@Override
	public void deleteAll(List<Long> toastIds) {
		List<ToastEntity> entities = toastRepository.findAllById(toastIds);
		toastRepository.deleteAll(entities);
	}
}
