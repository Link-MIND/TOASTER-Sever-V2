package com.app.toaster.application.port.common;

public interface CheckClipMemberPort {
    boolean checkUserInClipMember(Long userId, Long clipId);

}
