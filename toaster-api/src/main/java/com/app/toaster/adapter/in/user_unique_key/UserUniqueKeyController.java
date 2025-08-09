package com.app.toaster.adapter.in.user_unique_key;

import com.app.toaster.application.port.make_unique_key.ToasterUniqueKeyUseCase;
import com.app.toaster.application.port.make_unique_key.in.command.UpdateUniqueKeyCommand;
import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/users")
@Validated
class UserUniqueKeyController {

    private final ToasterUniqueKeyUseCase toasterUniqueKeyUseCase;

    /*
    uniquekey 생성시 validation 어노테이션으로
     */

    @PutMapping("/uniquekey")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> updateUniqueKey(@UserId Long userId, @Valid @RequestBody UpdateUniqueKeyRequest uniqueKeyRequestDto) {
        return ApiResponse.success(Success.CREATE_TOAST_SUCCESS, toasterUniqueKeyUseCase.updateUniqueKey(UpdateUniqueKeyCommand.ofCommand(uniqueKeyRequestDto.uniqueKey(), userId)));
    }

    @PostMapping("/uniquekey")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<?> makeUniqueKey() {
        return ApiResponse.success(Success.CREATE_UNIQUEKEY_SUCCESS, toasterUniqueKeyUseCase.makeUniqueKey());
    }


}
