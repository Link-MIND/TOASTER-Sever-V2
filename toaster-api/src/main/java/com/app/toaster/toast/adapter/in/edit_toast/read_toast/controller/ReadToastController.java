package com.app.toaster.toast.adapter.in.edit_toast.read_toast.controller;

import com.app.toaster.toast.adapter.in.read_toast.ReadToastCommand;
import com.app.toaster.toast.adapter.in.read_toast.ReadToastUseCase;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.toast.adapter.in.edit_toast.read_toast.request.ReadToastRequest;
import com.app.toaster.trace.aop.LogUpdateHistory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/toast")
@Validated
@Tag(name = "Toast", description = "토스트(링크 저장) 관련 API")
class ReadToastController {
	private final ReadToastUseCase readToastUseCase;

	@Operation(summary = "토스트 읽음 처리", description = "토스트 읽음 처리")
	@PatchMapping("{toastId}/read")
	@ResponseStatus(HttpStatus.OK)
	@LogUpdateHistory(eventString = "토스트 읽음 처리", domain = "TOAST")
	public ApiResponse readToast(
		//@UserId Long userId,
		@PathVariable Long toastId,
		@RequestBody ReadToastRequest request
	) {
		// todo-mh 임의로 userId 1L로 설정, 추후 변경
		ReadToastCommand command = request.toCommand(1L, toastId, request.isRead());
		readToastUseCase.readToast(command.userId(), command.toastId(), command.isRead());
		return ApiResponse.success(Success.UPDATE_ISREAD_SUCCESS);
	}
}
