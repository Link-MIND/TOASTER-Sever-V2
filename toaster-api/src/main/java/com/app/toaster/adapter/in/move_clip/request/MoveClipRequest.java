package com.app.toaster.adapter.in.move_clip.request;

import java.util.List;

public record MoveClipRequest(List<Long> toastIds, Long targetClipId) {
}