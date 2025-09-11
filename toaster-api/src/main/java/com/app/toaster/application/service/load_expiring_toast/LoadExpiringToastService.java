package com.app.toaster.application.service.load_expiring_toast;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.adapter.in.load_expiring_toast.ExpiringToastResponse;
import com.app.toaster.application.port.load_expiring_toaster.in.LoadExpiringToasterUseCase;
import com.app.toaster.application.port.load_expiring_toaster.out.LoadExpiringToastPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoadExpiringToastService implements LoadExpiringToasterUseCase {
	private final LoadExpiringToastPort loadExpiringToastPort;
	private final static int LIST_SIZE = 3;
	@Override
	@Transactional(readOnly = true)
	public List<ExpiringToastResponse> getExpiringToaster(Long userId) {
		return loadExpiringToastPort.loadExpiringToast(userId, LIST_SIZE)
			.stream().map(ExpiringToastResponse::toResponse)
			.toList();
	}
}
