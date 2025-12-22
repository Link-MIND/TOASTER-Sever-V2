package com.app.toaster.user.adapter.out.persistence.jpa;

import com.app.toaster.user.domain.models.ToasterUser;

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
