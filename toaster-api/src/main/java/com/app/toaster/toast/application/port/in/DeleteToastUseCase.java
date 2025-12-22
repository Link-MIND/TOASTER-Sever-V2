package com.app.toaster.toast.application.port.in;

import com.app.toaster.toast.adapter.in.delete_toast.DeleteToastCommand;

public interface DeleteToastUseCase {
	public void deleteToast(DeleteToastCommand command);
}
