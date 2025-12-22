package com.app.toaster.user.adapter.in.user_update_os;

import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.user.application.port.in.user_update_os.UpdateOsCommand;
import com.app.toaster.user.application.port.in.user_update_os.UpdateOsUseCase;
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
class UserUpdateOsController {

    private final UpdateOsUseCase updateOsUseCase;

    @PatchMapping("/os")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> updateAllowedNotification (@UserId Long userId, @RequestBody UpdateOsRequestDto updateOsDto) {
        return ApiResponse.success(Success.UPDATE_OS_SUCCESS, updateOsUseCase.updateOs(UpdateOsCommand.ofCommand(userId,updateOsDto.os())));
    }

}
