package com.app.toaster.application.service.restore_toast;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.adapter.out.persistence.burned_toast.BurnedToastPort;
import com.app.toaster.application.port.common.CheckToastOwnerPort;
import com.app.toaster.application.port.read_toast.out.UpdateToastPort;
import com.app.toaster.application.port.restore_toast.RestoreToastCommand;
import com.app.toaster.application.port.restore_toast.RestoreToastUseCase;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class RestoreToastService implements RestoreToastUseCase {

	private final CheckToastOwnerPort checkToastOwnerPort;
	private final UpdateToastPort updateToastPort;
	private final BurnedToastPort burnedToastPort;

	@Override
	@Transactional
	public void restoreToast(RestoreToastCommand command) {
		validate(command.userId(), command.toastId());
		updateToastPort.restoreToast(command.toastId());
		burnedToastPort.delete(command.toastId());
	}

	private void validate(Long userId, Long toastId){
		if (!checkToastOwnerPort.existsByIdAndUserId(toastId, userId)) {
			throw new CustomException(Error.UNAUTHORIZED_ACCESS, "해당 유저의 토스트가 아닙니다.");
		}
	}


}

