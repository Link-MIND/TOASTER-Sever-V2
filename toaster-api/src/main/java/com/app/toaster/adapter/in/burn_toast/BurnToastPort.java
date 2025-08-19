package com.app.toaster.adapter.in.burn_toast;

import java.time.LocalDate;
import java.util.List;

import com.app.toaster.toast.model.Toast;

public interface BurnToastPort {

	void markAsBurned(List<Long> toastIds);
}
