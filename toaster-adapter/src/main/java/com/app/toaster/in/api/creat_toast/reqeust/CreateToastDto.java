package com.app.toaster.in.api.creat_toast.reqeust;

import jakarta.annotation.Nullable;

public record CreateToastDto(String linkUrl, @Nullable Long clipId, boolean isTimerEnabled) {
}
