package com.app.toaster.adapter.in.load_expiring_toast;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.toaster.application.port.load_expiring_toaster.in.LoadExpiringToasterUseCase;
import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/toast")
public class LoadExpiringToastController {
	private final LoadExpiringToasterUseCase loadExpiringToastUseCase;

	@Operation(summary = "곧 만료되는 토스트", description = "홈화면 곧 만료되는 타이머")
	@GetMapping("/expiring")
	public ApiResponse<?> editToastTitle(
		@UserId Long userId
	) {
		return ApiResponse.success(Success.GET_EXPIRING_TOAST_SUCCESS, loadExpiringToastUseCase.getExpiringToaster(userId));
	}
}

