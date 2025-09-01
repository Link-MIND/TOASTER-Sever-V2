package com.app.toaster.application.service.delete_toast;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.application.port.common.CheckClipOwnerPort;
import com.app.toaster.application.port.common.CheckToastOwnerPort;
import com.app.toaster.application.port.delete_toast.in.DeleteToastCommand;
import com.app.toaster.application.port.delete_toast.in.DeleteToastUseCase;
import com.app.toaster.application.port.delete_toast.out.DeleteToastPort;
import com.app.toaster.application.port.load_toast.out.LoadToastPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.model.Toast;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteToastService implements DeleteToastUseCase {

	private final DeleteToastPort deleteToastPort;
	private final LoadToastPort loadToastPort;
	private final CheckClipOwnerPort checkClipOwnerPort;

	@Override
	@Transactional
	public void deleteToast(DeleteToastCommand command) {
		validate(command.userId(), command.toastIds());
		deleteToastPort.deleteAll(command.toastIds());
	}

	private void validate(Long userId, List<Long> toastIds) {
		toastIds.forEach(toastId -> {
			Long clipId = loadToastPort.loadToast(toastId).getClipId();
			if (clipId != 0L && !checkClipOwnerPort.checkClipPermission(clipId, userId)) {
				throw new CustomException(Error.UNAUTHORIZED_ACCESS, "해당 유저의 클립이 아닙니다.");
			}
		});
	}
}
