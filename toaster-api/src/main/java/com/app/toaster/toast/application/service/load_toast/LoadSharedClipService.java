package com.app.toaster.toast.application.service.load_toast;

import com.app.toaster.clip.adapter.in.load_shared_clip.response.SharedClipResponseDto;
import com.app.toaster.clip.adapter.in.load_shared_clip.response.ToastDto;
import com.app.toaster.clip.application.port.in.LoadSharedClipUseCase;
import com.app.toaster.clip.application.port.out.LoadSharedClipPort;
import com.app.toaster.clip.domain.model.Clip;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.NotFoundException;
import com.app.toaster.toast.application.port.out.LoadToastPort;
import com.app.toaster.toast.domain.toast.vo.ToastFilter;
import java.util.List;
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

        Page<ToastDto> toastsPage = loadToastPort.findToastsByClipId(clipId, filter, pageable);
        List<ToastDto> toasts = toastsPage.getContent();

        return SharedClipResponseDto.of(totalToastNum, unreadToastNum, burnedToastNum, toasts);
    }
}
