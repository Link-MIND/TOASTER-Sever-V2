package com.app.toaster.user.application.port.in.load_user_setting.command;

public record LoadUserSettingCommand(
    Long userId
) {
    public static LoadUserSettingCommand toCommand(Long userId) {
        return new LoadUserSettingCommand(userId);
    }
}
