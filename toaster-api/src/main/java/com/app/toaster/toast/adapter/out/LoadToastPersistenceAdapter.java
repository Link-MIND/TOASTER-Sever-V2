package com.app.toaster.toast.adapter.out;

import com.app.toaster.clip.adapter.in.load_shared_clip.response.ToastDto;
import com.app.toaster.clip.adapter.out.persistence.jpa.ClipMapper;
import com.app.toaster.clip.adapter.out.persistence.jpa.ClipRepository;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.application.port.out.LoadToastPort;
import com.app.toaster.toast.domain.toast.model.Toast;
import com.app.toaster.toast.domain.toast.vo.ToastFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadToastPersistenceAdapter implements LoadToastPort {

    private final ToastRepository toastRepository;
    private final ToastQueryRepository toastQueryRepository;
    private final ClipRepository clipRepository;
    private final ClipMapper clipMapper;

    @Override
    public Toast loadToast(Long toastId) {
        ToastEntity entity = toastRepository.findById(toastId)
            .orElseThrow(
                () -> new CustomException(Error.BAD_REQUEST_ID, Error.BAD_REQUEST_ID.getMessage()));

        return ToastMapper.toDomain(entity);
    }

    @Override
    public long countToastsByClipId(Long clipId) {
        return 0;
    }

    @Override
    public long countUnreadToastsByClipId(Long clipId) {
        return 0;
    }

    @Override
    public long countBurnedToastsByClipId(Long clipId) {
        return 0;
    }

    @Override
    public Page<ToastDto> findToastsByClipId(Long clipId, ToastFilter filter, Pageable pageable) {
        return toastQueryRepository.findToastsByClipId(
            clipId, filter, pageable
        );
    }
}
