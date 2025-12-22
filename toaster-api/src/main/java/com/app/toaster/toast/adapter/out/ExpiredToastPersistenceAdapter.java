package com.app.toaster.toast.adapter.out;

import com.app.toaster.toast.application.port.out.ExpiredToastPort;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
