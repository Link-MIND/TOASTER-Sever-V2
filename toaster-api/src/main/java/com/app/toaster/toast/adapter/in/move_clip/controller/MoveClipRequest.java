package com.app.toaster.toast.adapter.in.move_clip.controller;

import java.util.List;

public record MoveClipRequest(List<Long> toastIds, Long targetClipId) {
}