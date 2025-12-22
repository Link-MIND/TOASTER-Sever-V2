package com.app.toaster.toast.adapter.in.create_toast;

import jakarta.annotation.Nullable;

public record CreateToastRequest(String linkUrl, @Nullable Long clipId, boolean isTimerEnabled) {
}
