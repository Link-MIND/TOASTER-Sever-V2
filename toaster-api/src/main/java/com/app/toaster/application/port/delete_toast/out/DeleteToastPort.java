package com.app.toaster.application.port.delete_toast.out;

import java.util.List;

public interface DeleteToastPort {
	public void deleteAll(List<Long> toastIds);
}
