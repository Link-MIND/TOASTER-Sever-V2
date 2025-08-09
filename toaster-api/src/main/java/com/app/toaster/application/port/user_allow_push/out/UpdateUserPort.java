package com.app.toaster.application.port.user_allow_push.out;

public interface UpdateUserPort {
    void allowPush(long userId, boolean isAllowed);
}
