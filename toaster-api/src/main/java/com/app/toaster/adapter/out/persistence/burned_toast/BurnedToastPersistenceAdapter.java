package com.app.toaster.adapter.out.persistence.burned_toast;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BurnedToastPersistenceAdapter implements BurnedToastPort{
	private final BurnedToastRepository burnedToastRepository;
	@Override
	public void delete(Long toastId) {
		burnedToastRepository.deleteByToastId(toastId);
	}
}
