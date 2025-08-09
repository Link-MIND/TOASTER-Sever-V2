package com.app.toaster.move_clip.port.in;

import java.util.List;

public record MoveClipCommand(Long userId, List<Long> toastIds, Long targetClipId) {
}
