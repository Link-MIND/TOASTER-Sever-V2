package com.app.toaster.in.api.creat_toast.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.in.api.creat_toast.reqeust.CreateToastDto;
import com.app.toaster.toast.port.in.CreateToastUseCase;
import com.app.toaster.toast.port.in.command.CreateToastCommand;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/toast")
@Validated
@Tag(name = "Toast", description = "토스트(링크 저장) 관련 API")
class CreateToastController {
	private final CreateToastUseCase createToastUseCase;

	@Operation(summary = "토스트 생성", description = "새로운 링크 저장")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse createToast(
		// @UserId Long userId,
		@RequestBody CreateToastDto requestDto
	) {
		// todo-mh 임의로 userId 1L로 설정, 추후 변경
		CreateToastCommand command = new CreateToastCommand(
			1L,
			requestDto.linkUrl(),
			requestDto.clipId(),
			requestDto.isTimerEnabled()
		);

		createToastUseCase.createToast(command);
		return ApiResponse.success(Success.CREATE_TOAST_SUCCESS);
	}
}
