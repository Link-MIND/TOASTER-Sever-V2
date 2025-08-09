package com.app.toaster.application.port.user_allow_push.out;

import com.app.toaster.application.port.user.UpdateUserPort;

public interface UpdateAllowPushPort extends UpdateUserPort {
    void allowPush(long userId, boolean isAllowed);
}
