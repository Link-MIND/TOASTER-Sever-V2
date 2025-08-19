package com.app.toaster.application.port.burn_toast.out;

import java.util.List;

public interface BurnToastPort {

	void markAsBurned(List<Long> toastIds);
}
