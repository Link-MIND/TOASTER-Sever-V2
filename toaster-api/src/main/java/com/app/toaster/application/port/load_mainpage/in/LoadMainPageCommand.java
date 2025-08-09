package com.app.toaster.application.port.load_mainpage.in;

public record LoadMainPageCommand(
    Long userId
) {
    public static LoadMainPageCommand toCommand(Long userId) {
        return new LoadMainPageCommand(userId);
    }

}
