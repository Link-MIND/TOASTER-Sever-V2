package com.app.toaster.application.service.load_user_setting;

import com.app.toaster.adapter.in.load_user_settings.LoadUserSettingResponse;
import com.app.toaster.application.port.load_user_setting.in.LoadUserSettingUseCase;
import com.app.toaster.application.port.load_user_setting.in.command.LoadUserSettingCommand;
import com.app.toaster.application.port.load_user_setting.out.LoadUserPort;
import com.app.toaster.user.models.ToasterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
class LoadUserSettingService implements LoadUserSettingUseCase {

    private final LoadUserPort loadUserPort;
    @Override
    @Transactional(readOnly = true)
    public LoadUserSettingResponse loadUserSettings(LoadUserSettingCommand loadUserSettingCommand) {
        ToasterUser toasterUser = loadUserPort.findUser(loadUserSettingCommand.userId());
        return LoadUserSettingResponse.toResponse(toasterUser);
    }
}
