package com.app.toaster.toast.application.port.out;

import java.util.List;

public interface DeleteToastPort {
	public void deleteAll(List<Long> toastIds);
}
