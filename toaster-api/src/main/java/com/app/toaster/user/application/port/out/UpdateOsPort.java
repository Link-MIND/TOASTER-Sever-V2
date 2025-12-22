package com.app.toaster.user.application.port.out;

import com.app.toaster.user.domain.enums.OsType;

public interface UpdateOsPort extends UpdateUserPort {
    void updateOs(Long userId, OsType os);
}
