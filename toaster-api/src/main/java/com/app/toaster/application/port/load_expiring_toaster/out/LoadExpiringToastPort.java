package com.app.toaster.application.port.load_expiring_toaster.out;

import java.util.List;

import com.app.toaster.toast.model.Toast;

public interface LoadExpiringToastPort {

	List<Toast> loadExpiringToast(Long userId, int size);
}
