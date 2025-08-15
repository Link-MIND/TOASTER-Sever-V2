package com.app.toaster.application.service.edit_toast_title;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.application.port.edit_toast_title.in.EditToastTitleCommand;
import com.app.toaster.application.port.edit_toast_title.in.EditToastTitleUseCase;
import com.app.toaster.application.port.read_toast.out.UpdateToastPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditToastTitleService implements EditToastTitleUseCase {
	private final UpdateToastPort updateToastPort;

	@Override
	@Transactional
	public void editToastTitle(EditToastTitleCommand command) {
		updateToastPort.editToastTitle(command.toastId(), command.newTitle());
	}
}
