package com.app.toaster.application.port.load_expiring_toaster.in;

import java.util.List;

import com.app.toaster.adapter.in.load_expiring_toast.ExpiringToastResponse;

public interface LoadExpiringToasterUseCase {

	List<ExpiringToastResponse> getExpiringToaster(Long userId);
}
