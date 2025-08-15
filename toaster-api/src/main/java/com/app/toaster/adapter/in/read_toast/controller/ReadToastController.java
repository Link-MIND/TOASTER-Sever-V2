package com.app.toaster.adapter.in.read_toast.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.toaster.adapter.in.read_toast.request.ReadToastRequest;
import com.app.toaster.application.port.read_toast.in.ReadToastCommand;
import com.app.toaster.application.port.read_toast.in.ReadToastUseCase;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/toast")
@Validated
@Tag(name = "Toast", description = "토스트(링크 저장) 관련 API")
class ReadToastController {
	private final ReadToastUseCase readToastUseCase;

	@Operation(summary = "토스트 읽음 처리", description = "토스트 읽음 처리")
	@PatchMapping("/read")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse createToast(
		// @UserId Long userId,
		@RequestBody ReadToastRequest request
	) {
		// todo-mh 임의로 userId 1L로 설정, 추후 변경
		ReadToastCommand command = ReadToastCommand.toCommand(1L, request.toastId(), request.isRead());
		readToastUseCase.readToast(command);
		return ApiResponse.success(Success.UPDATE_ISREAD_SUCCESS);
	}
}
