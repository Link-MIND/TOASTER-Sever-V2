package com.app.toaster.adapter.in.user_allow_push;

import com.app.toaster.adapter.in.user_allow_push.command.UpdateAllowedPushCommand;
import com.app.toaster.application.port.user_allow_push.in.UserAllowPushUseCase;
import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
