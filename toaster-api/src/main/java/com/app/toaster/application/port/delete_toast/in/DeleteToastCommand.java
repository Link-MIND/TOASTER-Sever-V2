package com.app.toaster.application.port.delete_toast.in;

import java.util.List;

public record DeleteToastCommand(
	Long userId,
	List<Long> toastIds
) {
	public static DeleteToastCommand toCommand(Long userId, List<Long> toastIds){
		return new DeleteToastCommand(userId, toastIds);
	}
}
