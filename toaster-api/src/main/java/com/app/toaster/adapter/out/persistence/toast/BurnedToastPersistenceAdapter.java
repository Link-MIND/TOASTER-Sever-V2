package com.app.toaster.adapter.out.persistence.toast;

import org.springframework.stereotype.Component;

import com.app.toaster.application.port.burn_toast.out.BurnedToastPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BurnedToastPersistenceAdapter implements BurnedToastPort {
	private final BurnedToastRepository burnedToastRepository;
	@Override
	public void delete(Long toastId) {
		burnedToastRepository.deleteByToastId(toastId);
	}
}
