package com.app.toaster.user.application.port.out;

public interface UpdateUniqueKeyPort extends UpdateUserPort {
    void updateUniqueKey(String uniqueKey, Long userId);
}
