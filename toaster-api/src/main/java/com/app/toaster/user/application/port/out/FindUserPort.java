package com.app.toaster.user.application.port.out;

import com.app.toaster.user.domain.models.ToasterUser;

public interface FindUserPort {
    ToasterUser findById(Long userId);
}
