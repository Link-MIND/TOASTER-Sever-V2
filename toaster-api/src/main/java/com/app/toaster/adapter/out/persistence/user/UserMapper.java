package com.app.toaster.adapter.out.persistence.user;

import com.app.toaster.user.models.ToasterUser;

class UserMapper {

    public static ToasterUser toDomain(UserEntity user) {
        return ToasterUser.createToasterUser(
            user.getNickname(),
            user.getSocialId(),
            user.getRefreshToken(),
            user.getFcmToken(),
            user.getFcmIsAllowed(),
            user.getProfile(),
            user.getOs()
        );
    }
}
