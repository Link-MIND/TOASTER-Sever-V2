package com.app.toaster.toast.application.port.out;

import java.util.List;

public interface BurnToastPort {

	void markAsBurned(List<Long> toastIds);
}
