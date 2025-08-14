package com.app.toaster.adapter.in.create_toast.request;

import jakarta.annotation.Nullable;

public record CreateToastDto(String linkUrl, @Nullable Long clipId, boolean isTimerEnabled) {
}
