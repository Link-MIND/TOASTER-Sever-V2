package com.app.toaster.adapter.in.delete_toast;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.toaster.adapter.in.edit_toast_title.EditToastTitleRequest;
import com.app.toaster.application.port.delete_toast.in.DeleteToastCommand;
import com.app.toaster.application.port.delete_toast.in.DeleteToastUseCase;
import com.app.toaster.application.port.edit_toast_title.in.EditToastTitleCommand;
import com.app.toaster.application.port.edit_toast_title.in.EditToastTitleUseCase;
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
class DeleteToastController {
	private final DeleteToastUseCase deleteToastUseCase;

	@Operation(summary = "토스트 삭제", description = "1개 이상의 링크 삭제")
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse deleteToast(
		// @UserId Long userId,
		@RequestBody DeleteToastRequest request
	) {
		// todo-mh 임의로 userId 1L로 설정, 추후 변경
		DeleteToastCommand command = DeleteToastCommand.toCommand(1L, request.toastIds());
		deleteToastUseCase.deleteToast(command);
		return ApiResponse.success(Success.DELETE_TOAST_SUCCESS);
	}
}
