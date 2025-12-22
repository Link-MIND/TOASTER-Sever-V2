package com.app.toaster.toast.application.port.in;

import com.app.toaster.toast.adapter.in.create_toast.command.CreateToastCommand;

public interface CreateToastUseCase {

	void createToast(CreateToastCommand createToastCommand);
}
