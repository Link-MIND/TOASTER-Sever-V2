package com.app.toaster.page.adapter.in.load_mainpage;

public record LoadMainPageCommand(
    Long userId
) {
    public static LoadMainPageCommand toCommand(Long userId) {
        return new LoadMainPageCommand(userId);
    }

}
