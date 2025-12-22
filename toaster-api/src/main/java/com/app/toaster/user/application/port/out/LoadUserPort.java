package com.app.toaster.user.application.port.out;

import com.app.toaster.user.domain.models.ToasterUser;

public interface LoadUserPort {

	ToasterUser findUser(long userId);

}
