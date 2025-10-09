package com.app.toaster.adapter.in.load_shared_clip.response;

import org.springframework.data.domain.Page;

public record SharedClipResponseDto(
    String thumbnailUrl,
    long totalToastNum,    // 전체 토스트 개수
    long unreadToastNum,   // 전체 미열람 토스트 개수
    long burnedToastNum,   // 전체 타버린 토스트 개수
    Page<ToastDto> toasts // 필터링 및 페이징된 토스트 목록
) {
    public static SharedClipResponseDto of(String thumbnailUrl, long totalToastNum, long unreadToastNum, long burnedToastNum, Page<ToastDto> toasts){
        return new SharedClipResponseDto(thumbnailUrl, totalToastNum, unreadToastNum, burnedToastNum, toasts);
    }
}
