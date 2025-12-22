package com.app.toaster.toast.adapter.in.create_toast.command;

public record CreateToastCommand(Long userId, String linkUrl, Long clipId, boolean isTimerEnabled) {
}
