package com.app.toaster.user.application.service.user_allow_push;

import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.BadRequestException;
import com.app.toaster.user.adapter.in.user_allow_push.UpdateAllowedPushResponse;
import com.app.toaster.user.application.port.in.user_allow_push.UserAllowPushUseCase;
import com.app.toaster.user.application.port.in.user_allow_push.command.UpdateAllowedPushCommand;
import com.app.toaster.user.application.port.out.LoadUserPort;
import com.app.toaster.user.application.port.out.UpdateAllowPushPort;
import com.app.toaster.user.domain.models.ToasterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UserAllowPushService implements UserAllowPushUseCase {
    private final LoadUserPort loadUserPort;
    private final UpdateAllowPushPort updateAllowPushPort;

    @Override
    @Transactional
    public UpdateAllowedPushResponse allowPush(UpdateAllowedPushCommand command) {
        ToasterUser user = loadUserPort.findUser(command.userId());
        validatePushAllowStatus(user, command.allowedPush());
        updateAllowPushPort.allowPush(user.getId(), command.allowedPush());
        return UpdateAllowedPushResponse.toResponse(command.allowedPush());
    }

    private void validatePushAllowStatus(ToasterUser toasterUser, boolean isAllowed) {
        if (isAllowed == toasterUser.getFcmIsAllowed()) {   //같은 경우면 에러가 날 수 있으니 에러 띄움.
            throw new BadRequestException(Error.BAD_REQUEST_VALIDATION,
                Error.BAD_REQUEST_VALIDATION.getMessage());
        }
    }
}
