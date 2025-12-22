package com.app.toaster.toast.application.port.in;

import com.app.toaster.toast.adapter.in.edit_toast.EditToastTitleCommand;

public interface EditToastTitleUseCase {
	public void editToastTitle(EditToastTitleCommand command);
}
