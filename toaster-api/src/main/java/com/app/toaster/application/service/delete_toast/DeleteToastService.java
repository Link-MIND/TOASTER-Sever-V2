package com.app.toaster.application.service.delete_toast;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.application.port.common.CheckToastOwnerPort;
import com.app.toaster.application.port.delete_toast.in.DeleteToastCommand;
import com.app.toaster.application.port.delete_toast.in.DeleteToastUseCase;
import com.app.toaster.application.port.delete_toast.out.DeleteToastPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteToastService implements DeleteToastUseCase {

	private final DeleteToastPort deleteToastPort;
	private final CheckToastOwnerPort checkToastOwnerPort;

	@Override
	@Transactional
	public void deleteToast(DeleteToastCommand command) {
		validate(command.userId(), command.toastIds());
		deleteToastPort.deleteAll(command.toastIds());
	}

	private void validate(Long userId, List<Long> toastIds){
		if (!checkToastOwnerPort.allOwnedByUser(userId, toastIds)) {
			throw new CustomException(Error.INVALID_USER_ACCESS, Error.INVALID_USER_ACCESS.getMessage());
		}
	}
}
