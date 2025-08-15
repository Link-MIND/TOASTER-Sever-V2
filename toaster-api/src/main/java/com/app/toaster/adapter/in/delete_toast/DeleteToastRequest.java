package com.app.toaster.adapter.in.delete_toast;

import java.util.List;

public record DeleteToastRequest(
	List<Long> toastIds
) {
}
