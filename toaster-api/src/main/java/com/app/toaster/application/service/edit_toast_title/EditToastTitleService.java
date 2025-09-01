package com.app.toaster.application.service.edit_toast_title;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.application.port.common.CheckClipOwnerPort;
import com.app.toaster.application.port.edit_toast_title.in.EditToastTitleCommand;
import com.app.toaster.application.port.edit_toast_title.in.EditToastTitleUseCase;
import com.app.toaster.application.port.load_toast.out.LoadToastPort;
import com.app.toaster.application.port.read_toast.out.UpdateToastPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.model.Toast;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditToastTitleService implements EditToastTitleUseCase {
	private final UpdateToastPort updateToastPort;
	private final LoadToastPort loadToastPort;
	private final CheckClipOwnerPort checkClipOwnerPort;

	@Override
	@Transactional
	public void editToastTitle(EditToastTitleCommand command) {
		validate(command.userId(), command.toastId());
		updateToastPort.editToastTitle(command.toastId(), command.newTitle());
	}

	private void validate(Long userId, Long toastId){
		Long clipId = loadToastPort.loadToast(toastId).getClipId();
		if (clipId != 0L && !checkClipOwnerPort.checkClipPermission(clipId, userId)) {
			throw new CustomException(Error.UNAUTHORIZED_ACCESS, "해당 유저의 클립이 아닙니다.");
		}
	}
}
