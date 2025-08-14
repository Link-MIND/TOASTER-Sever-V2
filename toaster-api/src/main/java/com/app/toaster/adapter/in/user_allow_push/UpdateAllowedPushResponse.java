package com.app.toaster.adapter.in.user_allow_push;

public record UpdateAllowedPushResponse(
    boolean isAllowed
) {
    public static UpdateAllowedPushResponse toResponse(boolean isAllowed){
        return new UpdateAllowedPushResponse(isAllowed);
    }
}
