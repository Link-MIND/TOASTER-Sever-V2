package com.app.toaster.application.port.move_clip.in;

import java.util.List;

public record MoveClipCommand(Long userId, List<Long> toastIds, Long targetClipId) {
}
