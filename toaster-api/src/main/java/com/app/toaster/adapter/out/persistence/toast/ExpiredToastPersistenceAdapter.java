package com.app.toaster.adapter.out.persistence.toast;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.app.toaster.adapter.in.burn_toast.ExpiredToastPort;
import com.app.toaster.toast.model.Toast;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExpiredToastPersistenceAdapter implements ExpiredToastPort {
	private final ToastRepository toastRepository;
	@Override
	public List<Long> findExpiredToasts(LocalDate now) {
		return toastRepository.findByBurnedAtBeforeAndIsTimerEnabledTrueAndIsReadFalseAndIsBurnedFalse(now)
			.stream()
			.map(ToastEntity::getId)
			.toList();
	}

}
