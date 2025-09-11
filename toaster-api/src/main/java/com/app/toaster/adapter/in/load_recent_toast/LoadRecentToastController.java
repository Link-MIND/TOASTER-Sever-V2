package com.app.toaster.adapter.in.load_recent_toast;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.toaster.application.port.load_expiring_toaster.in.LoadExpiringToasterUseCase;
import com.app.toaster.application.port.load_recent_toast.in.LoadRecentToastUseCase;
import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/toast")
@Tag(name = "Toast", description = "토스트(링크 저장) 관련 API")
public class LoadRecentToastController {
	private final LoadRecentToastUseCase loadRecentToastUseCase;

	@Operation(summary = "최근 토스트 조회", description = "[홈화면] 최근 저장/공유받은 링크")
	@GetMapping("/recent")
	public ApiResponse<?> loadRecentToast(
		@UserId Long userId
	) {
		return ApiResponse.success(Success.GET_EXPIRING_TOAST_SUCCESS, loadRecentToastUseCase.getRecentToast(userId));
	}
}
