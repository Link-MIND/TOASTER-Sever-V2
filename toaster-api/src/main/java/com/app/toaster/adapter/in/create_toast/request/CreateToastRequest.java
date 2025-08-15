package com.app.toaster.adapter.in.create_toast.request;

import jakarta.annotation.Nullable;

public record CreateToastRequest(String linkUrl, @Nullable Long clipId, boolean isTimerEnabled) {
}
