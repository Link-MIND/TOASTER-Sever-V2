package com.app.toaster.clip.adapter.in.load_shared_clip.response;

import java.util.List;

public record SharedClipResponseDto(
    Long clipId,
    String clipTitle,
    long totalToastNum,    // 전체 토스트 개수
    long unreadToastNum,   // 전체 미열람 토스트 개수
    long burnedToastNum,   // 전체 타버린 토스트 개수
    List<ToastDto> toasts // 필터링 및 페이징된 토스트 목록

) {
    public static SharedClipResponseDto of(Long clipId, String clipTitle, long totalToastNum, long unreadToastNum, long burnedToastNum, List<ToastDto> toasts){
        return new SharedClipResponseDto(clipId, clipTitle, totalToastNum, unreadToastNum, burnedToastNum, toasts);
    }
}
