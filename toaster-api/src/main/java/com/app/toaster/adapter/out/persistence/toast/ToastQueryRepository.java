package com.app.toaster.adapter.out.persistence.toast;

import com.app.toaster.adapter.in.load_shared_clip.response.ToastDto;
import com.app.toaster.toast.vo.ToastFilter;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ToastQueryRepository {
    long bulkUpdateClipIdByIds(List<Long> toastIds, Long targetClipId);

    Page<ToastDto> findToastsByClipId(Long clipId, ToastFilter filter, Pageable pageable);

}
