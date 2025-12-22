package com.app.toaster.clip.application.service.move_clip;

import com.app.toaster.global.port.CheckClipOwnerPort;
import com.app.toaster.clip.adapter.in.move_clip.MoveClipCommand;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.adapter.out.UpdateToastClipPort;
import com.app.toaster.toast.application.port.in.MoveClipUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
