package com.app.toaster.user.application.port.in.user_update_os;

import com.app.toaster.user.domain.enums.OsType;

public record UpdateOsCommand(
    Long userId,
    OsType os
) {
    public static UpdateOsCommand ofCommand(Long userId, String os){
        return new UpdateOsCommand(userId, OsType.valueOf(os));
    }
}
