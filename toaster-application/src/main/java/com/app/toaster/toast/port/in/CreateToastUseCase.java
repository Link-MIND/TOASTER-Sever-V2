package com.app.toaster.toast.port.in;

import com.app.toaster.toast.port.in.command.CreateToastCommand;

public interface CreateToastUseCase {

	public void createToast(CreateToastCommand createToastCommand);
}
