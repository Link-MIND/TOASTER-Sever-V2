package com.app.toaster.toast.application.service.delete_toast;

import com.app.toaster.global.port.CheckClipOwnerPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.adapter.in.delete_toast.DeleteToastCommand;
import com.app.toaster.toast.application.port.in.DeleteToastUseCase;
import com.app.toaster.toast.application.port.out.DeleteToastPort;
import com.app.toaster.toast.application.port.out.LoadToastPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
