package com.app.toaster.application.service.read_toast;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.application.port.common.CheckToastOwnerPort;
import com.app.toaster.application.port.read_toast.in.ReadToastCommand;
import com.app.toaster.application.port.read_toast.in.ReadToastUseCase;
import com.app.toaster.application.port.read_toast.out.UpdateToastPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadToastService implements ReadToastUseCase {

	private final CheckToastOwnerPort checkToastOwnerPort;
	private final UpdateToastPort updateToastPort;

	@Override
	@Transactional
	public void readToast(ReadToastCommand command) {
		validate(command.userId(), command.toastId());
		updateToastPort.changeReadStatus(command.toastId(), command.isRead());
	}

	private void validate(Long userId, Long toastId){
		if (!checkToastOwnerPort.existsByIdAndUserId(toastId, userId)) {
			throw new CustomException(Error.UNAUTHORIZED_ACCESS, "해당 유저의 토스트가 아닙니다.");
		}
	}
}
