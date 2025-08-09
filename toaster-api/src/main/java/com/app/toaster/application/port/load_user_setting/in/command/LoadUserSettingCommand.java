package com.app.toaster.application.port.load_user_setting.in.command;

public record LoadUserSettingCommand(
    Long userId
) {
    public static LoadUserSettingCommand toCommand(Long userId) {
        return new LoadUserSettingCommand(userId);
    }
}
