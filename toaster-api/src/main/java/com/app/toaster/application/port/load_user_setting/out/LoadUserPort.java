package com.app.toaster.application.port.load_user_setting.out;

import com.app.toaster.user.models.ToasterUser;

public interface LoadUserPort {

	ToasterUser findUser(long userId);

}
