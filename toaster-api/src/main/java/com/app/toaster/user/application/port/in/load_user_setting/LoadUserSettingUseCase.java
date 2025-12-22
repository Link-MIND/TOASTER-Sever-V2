package com.app.toaster.user.application.port.in.load_user_setting;

import com.app.toaster.user.adapter.in.load_user_settings.LoadUserSettingResponse;
import com.app.toaster.user.application.port.in.load_user_setting.command.LoadUserSettingCommand;

public interface LoadUserSettingUseCase {
    LoadUserSettingResponse loadUserSettings(LoadUserSettingCommand loadUserSettingCommand);
}
