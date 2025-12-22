package com.app.toaster.user.application.port.in.user_allow_push.command;

import com.app.toaster.user.adapter.in.user_allow_push.UpdateAllowedPushRequest;

public record UpdateAllowedPushCommand(
    Long userId,
    boolean allowedPush
) {
    public static UpdateAllowedPushCommand ofCommand(Long userId, UpdateAllowedPushRequest request){
        return new UpdateAllowedPushCommand(userId, request.allowedPush());
    }
}
