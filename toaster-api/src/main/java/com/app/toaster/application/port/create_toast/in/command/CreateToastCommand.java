package com.app.toaster.application.port.create_toast.in.command;

public record CreateToastCommand(Long userId, String linkUrl, Long clipId, boolean isTimerEnabled) {
}
