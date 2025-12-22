package com.app.toaster.clip.adapter.in.load_shared_clip.response;

import com.querydsl.core.annotations.QueryProjection;

public record ToastDto(
    Long toastId,
    String modifiedBy, // "김수현님이 2025.09.08 수정"
    String title,
    String linkUrl,
    int dDay // 남은 날짜 (e.g., 3, 0, -1(엣지 케이스 있을까))
) {
    @QueryProjection
    public ToastDto(Long toastId, String modifiedBy, String title, String linkUrl, int dDay) {
        this.toastId = toastId;
        this.modifiedBy = modifiedBy;
        this.title = title;
        this.linkUrl = linkUrl;
        this.dDay = dDay;
    }
}
