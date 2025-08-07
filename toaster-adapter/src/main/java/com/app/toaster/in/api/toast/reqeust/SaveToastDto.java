package com.app.toaster.in.api.toast.reqeust;

import jakarta.annotation.Nullable;

public record SaveToastDto(String linkUrl, @Nullable Long clipId, boolean isTimerEnabled) {
}
