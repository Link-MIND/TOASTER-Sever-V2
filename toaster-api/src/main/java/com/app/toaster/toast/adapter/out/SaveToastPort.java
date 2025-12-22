package com.app.toaster.toast.adapter.out;

import com.app.toaster.toast.domain.toast.model.Toast;

public interface SaveToastPort {
	void save(Toast toast);
}