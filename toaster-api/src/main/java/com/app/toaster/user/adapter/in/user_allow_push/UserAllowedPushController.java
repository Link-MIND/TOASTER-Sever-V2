package com.app.toaster.user.adapter.in.user_allow_push;

import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.user.application.port.in.user_allow_push.UserAllowPushUseCase;
import com.app.toaster.user.application.port.in.user_allow_push.command.UpdateAllowedPushCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/users")
class UserAllowedPushController {

    private final UserAllowPushUseCase allowPushUseCase;

    @PatchMapping("/notification")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> updateAllowedNotification (@UserId Long userId, @RequestBody @Valid final UpdateAllowedPushRequest requestDto) {
        return ApiResponse.success(Success.UPDATE_PUSH_ALLOWED_SUCCESS, allowPushUseCase.allowPush(UpdateAllowedPushCommand.ofCommand(userId, requestDto)));
    }
}
