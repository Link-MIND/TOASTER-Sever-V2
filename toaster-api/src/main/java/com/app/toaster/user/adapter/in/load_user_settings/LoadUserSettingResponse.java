package com.app.toaster.user.adapter.in.load_user_settings;

import com.app.toaster.user.domain.models.ToasterUser;

public record LoadUserSettingResponse(
    String nickname,
    Boolean fcmIsAllowed
) {
    public static LoadUserSettingResponse toResponse(ToasterUser toasterUser) {
        return new LoadUserSettingResponse(toasterUser.getNickname(), toasterUser.getFcmIsAllowed());
    }
}
