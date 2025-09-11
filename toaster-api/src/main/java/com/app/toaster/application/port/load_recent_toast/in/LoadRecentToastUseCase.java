package com.app.toaster.application.port.load_recent_toast.in;

import java.util.List;

import com.app.toaster.adapter.in.load_recent_toast.RecentSavedToastResponse;
import com.app.toaster.adapter.in.load_recent_toast.RecentToastResponse;

public interface LoadRecentToastUseCase {
	RecentToastResponse getRecentToast(Long userId);
}
