package com.app.toaster.application.service.create_toast;

import com.app.toaster.application.port.common.CheckClipOwnerPort;
import com.app.toaster.application.port.create_toast.in.CreateToastUseCase;
import com.app.toaster.application.port.create_toast.in.command.CreateToastCommand;
import com.app.toaster.application.port.create_toast.out.FindClipOwnerPort;
import com.app.toaster.application.port.create_toast.out.ParseOgTagPort;
import com.app.toaster.application.port.create_toast.out.ParsedOgResult;
import com.app.toaster.application.port.create_toast.out.SaveToastPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.model.Toast;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
class SaveToastService implements CreateToastUseCase {

	private final SaveToastPort saveToastPort;
	private final ParseOgTagPort parseOgTagPort;
	private final FindClipOwnerPort loadUserPort;
	private final CheckClipOwnerPort checkClipOwnerPort;

	@Value("${static-image.root}")
	private String BASIC_ROOT;

	@Override
	@Transactional
	public void createToast(CreateToastCommand command) {
		//todo-mh 주석제거
		//validate(userId, clipId);

		ParsedOgResult res = parseOgTagPort.parse(command.linkUrl());
		String thumbnail = checkIsBasicImage(res.imageAdvanced());
		LocalDate burnedAt = command.isTimerEnabled()? LocalDate.now().plusDays(7) : null;

		Toast toast = Toast.create(command.userId(), command.clipId(), res.titleAdvanced(), command.linkUrl(), thumbnail, burnedAt, false, command.isTimerEnabled());
		saveToastPort.save(toast);
	}

	private void validate(Long userId, Long clipId) {
		if (!loadUserPort.existsById(userId)) {
			throw new CustomException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage());
		}

		if (clipId != null && !checkClipOwnerPort.checkClipPermission(clipId, userId)) {
			throw new CustomException(Error.UNAUTHORIZED_ACCESS, "해당 유저의 클립이 아닙니다.");
		}
	}

	private String checkIsBasicImage(String imageUrl){
		if (!imageUrl.startsWith("http")){
			return BASIC_ROOT+imageUrl;
		}
		return imageUrl;
	}
}
