package com.app.toaster.toast.adapter.in.delete_toast;

import java.util.List;

public record DeleteToastRequest(
	List<Long> toastIds
) {
}
