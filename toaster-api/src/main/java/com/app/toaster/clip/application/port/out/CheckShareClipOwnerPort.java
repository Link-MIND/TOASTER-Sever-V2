package com.app.toaster.clip.application.port.out;

public interface CheckShareClipOwnerPort {
    void check(Long clipId, Long userId);
}
