package com.app.toaster.application.port.create_toast.out;

import com.app.toaster.toast.model.Toast;

public interface SaveToastPort {
	void save(Toast toast);
}