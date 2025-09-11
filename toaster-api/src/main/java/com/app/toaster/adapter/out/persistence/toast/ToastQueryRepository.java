package com.app.toaster.adapter.out.persistence.toast;

import java.util.Collection;
import java.util.List;

import com.app.toaster.toast.enums.ClipType;

public interface ToastQueryRepository {
    long bulkUpdateClipIdByIds(List<Long> toastIds, Long targetClipId);

    List<ToastEntity> getExpiringToast(Long userId, int size);

	List<ToastEntity> getRecentToast(Long userId, int size, ClipType clipType);
}
