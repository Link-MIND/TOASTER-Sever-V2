package com.app.toaster.application.port.load_user_setting.in;

import com.app.toaster.adapter.in.load_user_settings.LoadUserSettingResponse;
import com.app.toaster.application.port.load_user_setting.in.command.LoadUserSettingCommand;

public interface LoadUserSettingUseCase {
    LoadUserSettingResponse loadUserSettings(LoadUserSettingCommand loadUserSettingCommand);
}
