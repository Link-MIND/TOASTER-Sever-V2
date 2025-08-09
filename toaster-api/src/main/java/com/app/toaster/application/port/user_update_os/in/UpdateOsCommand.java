package com.app.toaster.application.port.user_update_os.in;

import com.app.toaster.user.enums.OsType;

public record UpdateOsCommand(
    Long userId,
    OsType os
) {
    public static UpdateOsCommand ofCommand(Long userId, String os){
        return new UpdateOsCommand(userId, OsType.valueOf(os));
    }
}
