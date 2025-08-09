package com.app.toaster.adapter.out.persistence.toast;

import java.util.List;

public interface ToastQueryRepository {
    long bulkUpdateClipIdByIds(List<Long> toastIds, Long targetClipId);
}
