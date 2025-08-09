package com.app.toaster.application.port.user_allow_push.in;

import com.app.toaster.adapter.in.user_allow_push.UpdateAllowedPushResponse;
import com.app.toaster.adapter.in.user_allow_push.command.UpdateAllowedPushCommand;

public interface UserAllowPushUseCase {
    UpdateAllowedPushResponse allowPush(UpdateAllowedPushCommand command);
}
