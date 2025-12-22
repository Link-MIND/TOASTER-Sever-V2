package com.app.toaster.toast.adapter.in.move_clip.controller;

import com.app.toaster.clip.adapter.in.move_clip.MoveClipCommand;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.toast.application.port.in.MoveClipUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/toast")
@Validated
@Tag(name = "Toast", description = "토스트(링크 저장) 관련 API")
class MoveToastClipController {
	private final MoveClipUseCase moveClipUseCase;

	@Operation(summary = "토스트 클립 이동", description = "1개이상의 토스트 특정 클립으로 이동")
	@PatchMapping("/clip")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse createToast(
		// @UserId Long userId,
		@RequestBody MoveClipRequest request
	) {
		// todo-mh 임의로 userId 1L로 설정, 추후 변경
		MoveClipCommand command = new MoveClipCommand(
			1L,
			request.toastIds(),
			request.targetClipId()
		);

		moveClipUseCase.moveClip(command);
		return ApiResponse.success(Success.UPDATE_TOAST_CLIP_SUCCESS);
	}
}
