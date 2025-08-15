package com.app.toaster.adapter.in.edit_toast_title;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
class EditToastTitleController {
	private final EditToastTitleUseCase editToastTitleUseCase;

	@Operation(summary = "토스트 제목 수정", description = "링크 제목 수정")
	@PatchMapping("/title")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse editToastTitle(
		// @UserId Long userId,
		@RequestBody EditToastTitleRequest request
	) {
		// todo-mh 임의로 userId 1L로 설정, 추후 변경
		EditToastTitleCommand command = EditToastTitleCommand.toCommand(1L, request.toastId(), request.newTitle());
		editToastTitleUseCase.editToastTitle(command);
		return ApiResponse.success(Success.UPDATE_TOAST_TITLE_SUCCESS);
	}
}
