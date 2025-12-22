package com.app.toaster.clip.application.port.in;

import com.app.toaster.clip.adapter.in.load_shared_clip.response.SharedClipResponseDto;
import com.app.toaster.toast.domain.toast.vo.ToastFilter;
import org.springframework.data.domain.Pageable;

public interface LoadSharedClipUseCase {
    SharedClipResponseDto loadSharedClip(Long clipId, Pageable pageable, ToastFilter filter);
}
