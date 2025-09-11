package com.app.toaster.adapter.in.load_recent_toast;

import java.util.List;

public record RecentToastResponse(
	List<RecentSavedToastResponse> recentSavedToasts,
	List<RecentSharedToastResponse> recentSharedToasts
) {
}
