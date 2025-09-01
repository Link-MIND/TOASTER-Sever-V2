package com.app.toaster.application.service.restore_toast;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.application.port.burn_toast.out.BurnedToastPort;
import com.app.toaster.application.port.common.CheckClipOwnerPort;
import com.app.toaster.application.port.common.CheckToastOwnerPort;
import com.app.toaster.application.port.load_toast.out.LoadToastPort;
import com.app.toaster.application.port.read_toast.out.UpdateToastPort;
import com.app.toaster.application.port.restore_toast.RestoreToastCommand;
import com.app.toaster.application.port.restore_toast.RestoreToastUseCase;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.model.Toast;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class RestoreToastService implements RestoreToastUseCase {

	private final LoadToastPort loadToastPort;
	private final CheckClipOwnerPort checkClipOwnerPort;
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
		Long clipId = loadToastPort.loadToast(toastId).getClipId();
		if (clipId != 0L && !checkClipOwnerPort.checkClipPermission(clipId, userId)) {
			throw new CustomException(Error.UNAUTHORIZED_ACCESS, "해당 유저의 클립이 아닙니다.");
		}
	}
}

