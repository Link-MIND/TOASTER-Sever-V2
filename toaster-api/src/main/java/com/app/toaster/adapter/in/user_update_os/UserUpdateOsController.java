package com.app.toaster.adapter.in.user_update_os;

import com.app.toaster.application.port.user_update_os.in.UpdateOsCommand;
import com.app.toaster.application.port.user_update_os.in.UpdateOsUseCase;
import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
