package com.app.toaster.user.application.port.out;

import com.app.toaster.user.application.port.out.UpdateUserPort;

public interface UpdateAllowPushPort extends UpdateUserPort {
    void allowPush(long userId, boolean isAllowed);
}
