package com.app.toaster.toast.adapter.in.delete_toast;

import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.toast.application.port.in.DeleteToastUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
class DeleteToastController {
	private final DeleteToastUseCase deleteToastUseCase;

	@Operation(summary = "토스트 삭제", description = "1개 이상의 링크 삭제")
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<?> deleteToast(
		// @UserId Long userId,
		@PathVariable(value = "userId") Long userId,
		@RequestBody DeleteToastRequest request
	) {
		// todo-mh 임의로 userId 1L로 설정, 추후 변경
		DeleteToastCommand command = DeleteToastCommand.toCommand(userId, request.toastIds());
		deleteToastUseCase.deleteToast(command);
		return ApiResponse.success(Success.DELETE_TOAST_SUCCESS);
	}
}
