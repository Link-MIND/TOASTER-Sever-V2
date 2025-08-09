package com.app.toaster.application.service.move_clip;

import com.app.toaster.application.port.CheckClipOwnerPort;
import com.app.toaster.application.port.move_clip.in.MoveClipCommand;
import com.app.toaster.application.port.move_clip.in.MoveClipUseCase;
import com.app.toaster.application.port.move_clip.out.UpdateToastClipPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
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
