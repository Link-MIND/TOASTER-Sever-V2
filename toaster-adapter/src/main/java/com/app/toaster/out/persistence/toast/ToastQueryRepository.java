package com.app.toaster.out.persistence.toast;

import java.util.List;

public interface ToastQueryRepository {
    long bulkUpdateClipIdByIds(List<Long> toastIds, Long targetClipId);
}
