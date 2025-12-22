package com.app.toaster.toast.adapter.out;

import com.app.toaster.toast.application.port.out.DeleteToastPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
