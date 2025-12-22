package com.app.toaster.user.adapter.in.load_user_settings;

import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.user.application.port.in.load_user_setting.LoadUserSettingUseCase;
import com.app.toaster.user.application.port.in.load_user_setting.command.LoadUserSettingCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/users")
@RequiredArgsConstructor
class LoadUserSettingController {

    private final LoadUserSettingUseCase loadUserSettingUseCase;

    @GetMapping("/settings")
    protected ApiResponse<?> LoadUserSetting(Long userId){ //jwt추가하고 어노테이션으로 변경
        return ApiResponse.success(Success.GET_SETTINGS_SUCCESS, loadUserSettingUseCase.loadUserSettings(LoadUserSettingCommand.toCommand(userId)));
    }


}
