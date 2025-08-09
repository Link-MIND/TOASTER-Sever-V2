package com.app.toaster.adapter.in.create_toast.in.controller;

import com.app.toaster.adapter.in.create_toast.in.request.CreateToastDto;
import com.app.toaster.application.port.create_toast.in.CreateToastUseCase;
import com.app.toaster.application.port.create_toast.in.command.CreateToastCommand;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
