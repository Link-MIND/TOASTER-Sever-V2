package com.app.toaster.clip.adapter.in.load_shared_clip.response;

import org.springframework.data.domain.Page;

public record SharedClipResponseDto(
    long totalToastNum,    // 전체 토스트 개수
    long unreadToastNum,   // 전체 미열람 토스트 개수
    long burnedToastNum,   // 전체 타버린 토스트 개수
    Page<ToastDto> toasts // 필터링 및 페이징된 토스트 목록

) {
    public static SharedClipResponseDto of(long totalToastNum, long unreadToastNum, long burnedToastNum, Page<ToastDto> toasts){
        return new SharedClipResponseDto(totalToastNum, unreadToastNum, burnedToastNum, toasts);
    }
}
