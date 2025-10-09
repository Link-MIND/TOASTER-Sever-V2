package com.app.toaster.application.port.load_toast.out;

import com.app.toaster.adapter.in.load_shared_clip.response.ToastDto;
import com.app.toaster.toast.model.Toast;
import com.app.toaster.toast.vo.ToastFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadToastPort {
	Toast loadToast(Long toastId);
	long countToastsByClipId(Long clipId);

	long countUnreadToastsByClipId(Long clipId);

	long countBurnedToastsByClipId(Long clipId);

	Page<ToastDto> findToastsByClipId(Long clipId, ToastFilter filter, Pageable pageable);
}
