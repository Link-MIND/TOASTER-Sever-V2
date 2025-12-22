package com.app.toaster.toast.application.service.read_toast;

import com.app.toaster.global.port.CheckClipOwnerPort;
import com.app.toaster.toast.adapter.in.read_toast.ReadToastUseCase;
import com.app.toaster.toast.adapter.out.UpdateToastPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.application.port.out.LoadToastPort;
import com.app.toaster.trace.aop.DomainId;
import com.app.toaster.trace.aop.LogUpdateHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadToastService implements ReadToastUseCase {

	private final CheckClipOwnerPort checkClipOwnerPort;
	private final LoadToastPort loadToastPort;
	private final UpdateToastPort updateToastPort;

	@Override
	@Transactional
	@LogUpdateHistory(eventString = "토스트 열람", domain = "TOAST")
	public void readToast(Long userId, @DomainId Long toastId, Boolean isRead) {
		validate(userId, toastId);
		updateToastPort.changeReadStatus(toastId, isRead);
	}

	private void validate(Long userId, Long toastId){
		Long clipId = loadToastPort.loadToast(toastId).getClipId();
		if (clipId != 0L && !checkClipOwnerPort.checkClipPermission(clipId, userId)) {
			throw new CustomException(Error.UNAUTHORIZED_ACCESS, "해당 유저의 클립이 아닙니다.");
		}
	}
}
