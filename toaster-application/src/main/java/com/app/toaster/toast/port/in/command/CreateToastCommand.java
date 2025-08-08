package com.app.toaster.toast.port.in.command;

public record CreateToastCommand(Long userId, String linkUrl, Long clipId, boolean isTimerEnabled) {
}
