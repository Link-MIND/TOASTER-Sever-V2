package com.app.toaster.user.models;

import lombok.Getter;

@Getter
public class ToasterUser {

    private final Long id;
    private String nickname;
    private String socialId;
    private String refreshToken;
    private String fcmToken;
    private Boolean fcmIsAllowed;
    private String profile;

    protected ToasterUser(Long id, String nickname, String socialId, String refreshToken, String fcmToken, Boolean fcmIsAllowed, String profile) {
        this.id = id;
        this.nickname = nickname;
        this.socialId = socialId;
        this.refreshToken = refreshToken;
        this.fcmToken = fcmToken;
        this.fcmIsAllowed = fcmIsAllowed;
        this.profile = profile;
    }

    public static ToasterUser createToasterUser(String nickname, String socialId, String refreshToken, String fcmToken, Boolean fcmIsAllowed, String profile){
        return new ToasterUser(null, nickname, socialId, refreshToken, fcmToken, fcmIsAllowed, profile);
    }
}
