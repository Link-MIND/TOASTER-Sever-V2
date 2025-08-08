package com.app.toaster.in.api.move_clip.reqeust;

import java.util.List;

import jakarta.annotation.Nullable;

public record MoveClipRequest(List<Long> toastIds, Long targetClipId) {
}