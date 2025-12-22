package com.app.toaster.user.application.service.load_user_setting;

import com.app.toaster.user.adapter.in.load_user_settings.LoadUserSettingResponse;
import com.app.toaster.user.application.port.in.load_user_setting.LoadUserSettingUseCase;
import com.app.toaster.user.application.port.in.load_user_setting.command.LoadUserSettingCommand;
import com.app.toaster.user.application.port.out.LoadUserPort;
import com.app.toaster.user.domain.models.ToasterUser;
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
