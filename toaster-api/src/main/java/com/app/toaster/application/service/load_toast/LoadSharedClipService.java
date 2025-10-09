package com.app.toaster.application.service.load_toast;

import com.app.toaster.adapter.in.load_shared_clip.response.SharedClipResponseDto;
import com.app.toaster.adapter.in.load_shared_clip.response.ToastDto;
import com.app.toaster.application.port.in.LoadSharedClipUseCase;
import com.app.toaster.application.port.load_toast.out.LoadToastPort;
import com.app.toaster.application.port.out.LoadSharedClipPort;
import com.app.toaster.clip.model.Clip;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.NotFoundException;
import com.app.toaster.toast.vo.ToastFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoadSharedClipService implements LoadSharedClipUseCase {

    private final LoadSharedClipPort loadSharedClipPort;
    private final LoadToastPort loadToastPort;

    @Override
    @Transactional(readOnly = true)
    public SharedClipResponseDto loadSharedClip(Long clipId, Pageable pageable, ToastFilter filter) {
        Clip clip = loadSharedClipPort.findClipById(clipId);
        if (clip == null){
            throw new NotFoundException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage());
        }
        long totalToastNum = loadToastPort.countToastsByClipId(clipId);
        long unreadToastNum = loadToastPort.countUnreadToastsByClipId(clipId);
        long burnedToastNum = loadToastPort.countBurnedToastsByClipId(clipId);

        Page<ToastDto> toasts = loadToastPort.findToastsByClipId(clipId, filter, pageable);

        return SharedClipResponseDto.of(clip.getThumbnail(), totalToastNum, unreadToastNum, burnedToastNum, toasts);
    }
}
