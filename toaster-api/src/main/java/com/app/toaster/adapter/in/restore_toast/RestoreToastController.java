package com.app.toaster.adapter.in.restore_toast;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.toaster.adapter.in.read_toast.request.ReadToastRequest;
import com.app.toaster.application.port.read_toast.in.ReadToastCommand;
import com.app.toaster.application.port.read_toast.in.ReadToastUseCase;
import com.app.toaster.application.port.restore_toast.RestoreToastCommand;
import com.app.toaster.application.port.restore_toast.RestoreToastUseCase;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/toast")
@Validated
@Tag(name = "Toast", description = "토스트(링크 저장) 관련 API")
class RestoreToastController {
	private final RestoreToastUseCase restoreToastUseCase;

	@Operation(summary = "타버린 토스트 복구", description = "타버린 토스트 복구(저장기간+7일)")
	@PatchMapping("/{toastId}/restore")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse restoreToast(
		// @UserId Long userId,
		@PathVariable Long toastId
	) {
		// todo-mh 임의로 userId 1L로 설정, 추후 변경
		RestoreToastCommand command = RestoreToastCommand.toCommand(1L, toastId);
		restoreToastUseCase.restoreToast(command);
		return ApiResponse.success(Success.RESTORE_TOAST_SUCCESS);
	}
}
