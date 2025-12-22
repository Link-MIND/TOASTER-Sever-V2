package com.app.toaster.toast.adapter.out;

import com.app.toaster.toast.application.port.out.BurnedToastPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BurnedToastPersistenceAdapter implements BurnedToastPort {
	private final BurnedToastRepository burnedToastRepository;
	@Override
	public void delete(Long toastId) {
		burnedToastRepository.deleteByToastId(toastId);
	}
}
