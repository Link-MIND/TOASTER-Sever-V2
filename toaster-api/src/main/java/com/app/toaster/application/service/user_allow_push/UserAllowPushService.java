package com.app.toaster.application.service.user_allow_push;

import com.app.toaster.adapter.in.user_allow_push.UpdateAllowedPushResponse;
import com.app.toaster.adapter.in.user_allow_push.command.UpdateAllowedPushCommand;
import com.app.toaster.application.port.load_user_setting.out.LoadUserPort;
import com.app.toaster.application.port.user_allow_push.in.UserAllowPushUseCase;
import com.app.toaster.application.port.user_allow_push.out.UpdateUserPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.BadRequestException;
import com.app.toaster.user.models.ToasterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UserAllowPushService implements UserAllowPushUseCase {
    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    @Override
    @Transactional
    public UpdateAllowedPushResponse allowPush(UpdateAllowedPushCommand command) {
        ToasterUser user = loadUserPort.findUser(command.userId());
        validatePushAllowStatus(user, command.allowedPush());
        updateUserPort.allowPush(user.getId(), command.allowedPush());
        return UpdateAllowedPushResponse.toResponse(command.allowedPush());
    }

    private void validatePushAllowStatus(ToasterUser toasterUser, boolean isAllowed) {
        if (isAllowed == toasterUser.getFcmIsAllowed()) {   //같은 경우면 에러가 날 수 있으니 에러 띄움.
            throw new BadRequestException(Error.BAD_REQUEST_VALIDATION,
                Error.BAD_REQUEST_VALIDATION.getMessage());
        }
    }
}
