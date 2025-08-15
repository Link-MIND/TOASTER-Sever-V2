package com.app.toaster.adapter.in.edit_toast_title;

import com.app.toaster.exception.Severity;
import com.app.toaster.valid.TitleValid;
import com.app.toaster.valid.ToastValidationGroup;

import jakarta.validation.constraints.NotNull;

public record UpdateToastDto(
	Long toastId,
	@TitleValid(payload = Severity.Error.class, groups = {ToastValidationGroup.class}) @NotNull String title
) {
	public static UpdateToastDto of(Long toastId, String title){
		return new UpdateToastDto(toastId,title);
	}
}
