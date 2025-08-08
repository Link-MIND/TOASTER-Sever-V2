package com.app.toaster.in.api.toast.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.in.api.toast.reqeust.SaveToastDto;
import com.app.toaster.toast.service.SaveToastService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/toast")
@Validated
@Tag(name = "Toast", description = "토스트(링크 저장) 관련 API")
class ToastController {
	private final SaveToastService toastService;

	@Operation(summary = "토스트 생성", description = "새로운 링크 저장")
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse createToast(
		// @UserId Long userId,
		@RequestBody SaveToastDto requestDto
	) {
		// toastService.createToast(userId, requestDto.linkUrl(), requestDto.clipId());
		// todo-mh 임의로 userId 1L로 설정, 추후 변경
		toastService.createToast(1L, requestDto.linkUrl(), requestDto.clipId(), requestDto.isTimerEnabled());
		return ApiResponse.success(Success.CREATE_TOAST_SUCCESS);
	}
}
