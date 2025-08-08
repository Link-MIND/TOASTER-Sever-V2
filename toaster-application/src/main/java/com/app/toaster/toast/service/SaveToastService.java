package com.app.toaster.toast.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.model.Toast;
import com.app.toaster.toast.port.in.CreateToastUseCase;
import com.app.toaster.toast.port.in.command.CreateToastCommand;
import com.app.toaster.toast.port.out.LoadClipPort;
import com.app.toaster.toast.port.out.LoadUserPort;
import com.app.toaster.toast.port.out.ParseOgTagPort;
import com.app.toaster.toast.port.out.ParsedOgResult;
import com.app.toaster.toast.port.out.SaveToastPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveToastService implements CreateToastUseCase {

	private final SaveToastPort saveToastPort;
	private final ParseOgTagPort parseOgTagPort;
	private final LoadUserPort loadUserPort;
	private final LoadClipPort loadClipPort;

	@Value("${static-image.root}")
	private String BASIC_ROOT;

	@Override
	@Transactional
	public void createToast(CreateToastCommand command) {
		//todo-mh 주석제거
		//validate(userId, clipId);

		ParsedOgResult res = parseOgTagPort.parse(command.linkUrl());
		String thumbnail = checkIsBasicImage(res.imageAdvanced());
		LocalDateTime burnedAt = command.isTimerEnabled()? LocalDateTime.now().plusDays(7) : null;

		Toast toast = Toast.create(command.userId(), command.clipId(), res.titleAdvanced(), command.linkUrl(), thumbnail, burnedAt, command.isTimerEnabled());
		saveToastPort.save(toast);
	}

	private void validate(Long userId, Long clipId) {
		if (!loadUserPort.existsById(userId)) {
			throw new CustomException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage());
		}

		if (clipId != null && !loadClipPort.existsByIdAndUserId(clipId, userId)) {
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
