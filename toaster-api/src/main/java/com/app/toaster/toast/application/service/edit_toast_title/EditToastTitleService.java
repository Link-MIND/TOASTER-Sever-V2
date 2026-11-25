package com.app.toaster.toast.application.service.edit_toast_title;

import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.global.port.CheckClipOwnerPort;
import com.app.toaster.toast.adapter.in.edit_toast.edit_title.EditToastTitleCommand;
import com.app.toaster.toast.adapter.out.UpdateToastPort;
import com.app.toaster.toast.application.port.in.EditToastTitleUseCase;
import com.app.toaster.toast.application.port.out.LoadToastPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
