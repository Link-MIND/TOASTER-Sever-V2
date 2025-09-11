package com.app.toaster.application.port.load_recent_toast.out;

import java.util.List;

import com.app.toaster.toast.enums.ClipType;
import com.app.toaster.toast.model.Toast;

public interface LoadRecentToastPort {

	List<Toast> loadRecentToast(Long userId, int size, ClipType clipType);
}
