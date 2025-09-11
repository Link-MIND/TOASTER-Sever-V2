package com.app.toaster.adapter.out.persistence.toast;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.toaster.application.port.load_expiring_toaster.out.LoadExpiringToastPort;
import com.app.toaster.application.port.load_recent_toast.out.LoadRecentToastPort;
import com.app.toaster.toast.enums.ClipType;
import com.app.toaster.toast.model.Toast;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ToastQueryPersistenceAdapter implements LoadExpiringToastPort, LoadRecentToastPort {

	private final ToastQueryRepository toastQueryRepository;
	@Override
	public List<Toast> loadExpiringToast(Long userId, int size) {
		return toastQueryRepository.getExpiringToast(userId, size)
			.stream()
			.map(ToastMapper::toDomain)
			.toList();
	}

	@Override
	public List<Toast> loadRecentToast(Long userId, int size, ClipType clipType) {
		return toastQueryRepository.getRecentToast(userId, size, clipType)
			.stream()
			.map(ToastMapper::toDomain)
			.toList();
	}
}
