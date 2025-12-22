package com.app.toaster.user.application.port.in.user_allow_push;

import com.app.toaster.user.adapter.in.user_allow_push.UpdateAllowedPushResponse;
import com.app.toaster.user.application.port.in.user_allow_push.command.UpdateAllowedPushCommand;

public interface UserAllowPushUseCase {
    UpdateAllowedPushResponse allowPush(UpdateAllowedPushCommand command);
}
