package com.app.toaster.toast.adapter.in.delete_toast;

import java.util.List;

public record DeleteToastCommand(
	Long userId,
	List<Long> toastIds
) {
	public static DeleteToastCommand toCommand(Long userId, List<Long> toastIds){
		return new DeleteToastCommand(userId, toastIds);
	}
}
