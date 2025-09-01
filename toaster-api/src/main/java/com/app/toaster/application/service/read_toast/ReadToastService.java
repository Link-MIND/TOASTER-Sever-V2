package com.app.toaster.application.service.read_toast;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.application.port.common.CheckClipOwnerPort;
import com.app.toaster.application.port.common.CheckToastOwnerPort;
import com.app.toaster.application.port.load_toast.out.LoadToastPort;
import com.app.toaster.application.port.read_toast.in.ReadToastCommand;
import com.app.toaster.application.port.read_toast.in.ReadToastUseCase;
import com.app.toaster.application.port.read_toast.out.UpdateToastPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.model.Toast;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadToastService implements ReadToastUseCase {

	private final CheckClipOwnerPort checkClipOwnerPort;
	private final LoadToastPort loadToastPort;
	private final UpdateToastPort updateToastPort;

	@Override
	@Transactional
	public void readToast(ReadToastCommand command) {
		validate(command.userId(), command.toastId());
		updateToastPort.changeReadStatus(command.toastId(), command.isRead());
	}

	private void validate(Long userId, Long toastId){
		Long clipId = loadToastPort.loadToast(toastId).getClipId();
		if (clipId != 0L && !checkClipOwnerPort.checkClipPermission(clipId, userId)) {
			throw new CustomException(Error.UNAUTHORIZED_ACCESS, "해당 유저의 클립이 아닙니다.");
		}
	}
}
