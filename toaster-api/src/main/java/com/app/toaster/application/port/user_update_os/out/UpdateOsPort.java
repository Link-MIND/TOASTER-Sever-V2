package com.app.toaster.application.port.user_update_os.out;

import com.app.toaster.application.port.user.UpdateUserPort;
import com.app.toaster.user.enums.OsType;

public interface UpdateOsPort extends UpdateUserPort {
    void updateOs(Long userId, OsType os);
}
