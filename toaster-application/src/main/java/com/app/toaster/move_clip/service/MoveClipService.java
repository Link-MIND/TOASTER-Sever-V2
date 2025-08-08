package com.app.toaster.move_clip.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.common.clip.port.out.CheckClipOwnerPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.move_clip.port.in.MoveClipCommand;
import com.app.toaster.move_clip.port.in.MoveClipUseCase;
import com.app.toaster.move_clip.port.out.UpdateToastClipPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoveClipService implements MoveClipUseCase {
	private final CheckClipOwnerPort checkClipOwnerPort;
	private final UpdateToastClipPort updateToastClipPort;

	@Override
	@Transactional
	public void moveClip(MoveClipCommand command) {
		validate(command.userId(), command.targetClipId());
		updateToastClipPort.updateToastClip(command.toastIds(), command.targetClipId());
	}

	private void validate(Long userId, Long clipId) {
		if (clipId != null && !checkClipOwnerPort.existsByIdAndUserId(clipId, userId)) {
			throw new CustomException(Error.UNAUTHORIZED_ACCESS, "해당 유저의 클립이 아닙니다.");
		}
	}
}
