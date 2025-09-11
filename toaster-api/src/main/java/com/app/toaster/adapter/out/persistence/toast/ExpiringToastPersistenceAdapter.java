package com.app.toaster.adapter.out.persistence.toast;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.toaster.application.port.load_expiring_toaster.out.LoadExpiringToastPort;
import com.app.toaster.toast.model.Toast;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExpiringToastPersistenceAdapter implements LoadExpiringToastPort {

	private final ToastQueryRepository toastQueryRepository;
	@Override
	public List<Toast> loadExpiringToast(Long userId, int size) {
		return toastQueryRepository.getExpiringToast(userId, size)
			.stream()
			.map(ToastMapper::toDomain)
			.toList();
	}
}
