package com.app.toaster.application.port.make_unique_key.out;

import com.app.toaster.application.port.user.UpdateUserPort;

public interface UpdateUniqueKeyPort extends UpdateUserPort {
    void updateUniqueKey(String uniqueKey, Long userId);
}
