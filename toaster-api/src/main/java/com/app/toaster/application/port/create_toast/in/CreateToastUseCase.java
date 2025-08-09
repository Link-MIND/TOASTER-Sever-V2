package com.app.toaster.application.port.create_toast.in;

import com.app.toaster.application.port.create_toast.in.command.CreateToastCommand;

public interface CreateToastUseCase {

	void createToast(CreateToastCommand createToastCommand);
}
