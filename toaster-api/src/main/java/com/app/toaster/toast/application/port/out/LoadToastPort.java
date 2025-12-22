package com.app.toaster.toast.application.port.out;

import com.app.toaster.clip.adapter.in.load_shared_clip.response.ToastDto;
import com.app.toaster.toast.domain.toast.model.Toast;
import com.app.toaster.toast.domain.toast.vo.ToastFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadToastPort {
	Toast loadToast(Long toastId);
	long countToastsByClipId(Long clipId);

	long countUnreadToastsByClipId(Long clipId);

	long countBurnedToastsByClipId(Long clipId);

	Page<ToastDto> findToastsByClipId(Long clipId, ToastFilter filter, Pageable pageable);
}
