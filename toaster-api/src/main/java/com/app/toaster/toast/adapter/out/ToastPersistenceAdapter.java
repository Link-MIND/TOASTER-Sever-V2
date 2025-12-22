package com.app.toaster.toast.adapter.out;

import com.app.toaster.global.port.CheckToastOwnerPort;
import com.app.toaster.toast.domain.toast.model.Toast;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToastPersistenceAdapter implements SaveToastPort, CheckToastOwnerPort {

	private final ToastRepository toastRepository;

	@Override
	public void save(Toast toast) {
		ToastEntity entity = ToastMapper.toEntity(toast);
		toastRepository.save(entity);
	}

	@Override
	public boolean existsByIdAndUserId(Long toastId, Long userId) {
		return toastRepository.existsByIdAndUserId(toastId, userId);
	}

	@Override
	public boolean allOwnedByUser(Long userId, List<Long> toastIds) {
		long count = toastRepository.countByIdInAndUserId(toastIds, userId);
		return count == toastIds.size();
	}
}