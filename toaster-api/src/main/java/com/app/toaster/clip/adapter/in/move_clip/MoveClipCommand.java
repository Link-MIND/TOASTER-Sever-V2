package com.app.toaster.clip.adapter.in.move_clip;

import java.util.List;

public record MoveClipCommand(Long userId, List<Long> toastIds, Long targetClipId) {
}
