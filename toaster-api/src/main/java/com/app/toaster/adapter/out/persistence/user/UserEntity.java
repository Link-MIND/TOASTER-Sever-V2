package com.app.toaster.adapter.out.persistence.user;

import com.app.toaster.entity.BaseTimeEntity;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.user.enums.OsType;
import com.app.toaster.user.enums.SocialType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
class UserEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String socialId;

	@Column(nullable = true)
	private String refreshToken;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SocialType socialType;

	@Column(nullable = true)
	private String fcmToken;

	@Column(nullable = true)
	private Boolean fcmIsAllowed = true;

	@Column(nullable = true)
	private String profile;

	@Column(nullable = false)
	private OsType os;

	@Column(nullable = true)
	private String uniqueKey;

	@Builder
	public UserEntity(String nickname, String socialId, SocialType socialType) {
		this.nickname = nickname;
		this.socialId = socialId;
		this.socialType = socialType;
	}

	public void updateFcmIsAllowed(boolean isAllowed) {
		this.fcmIsAllowed = isAllowed;
	}

	public void updateOs(OsType os) {
		if (os != null) {
			this.os = os;
			return;
		}
		throw new CustomException(Error.BAD_REQUEST_OS, Error.BAD_REQUEST_OS.getMessage());
	}

	public void updateUniqueKey(String uniqueKey) {
		if (uniqueKey != null && !uniqueKey.isBlank()) {
			this.uniqueKey = uniqueKey;
			return;
		}
		throw new CustomException(Error.BAD_REQUEST_UNIQUEKEY, Error.BAD_REQUEST_UNIQUEKEY.getMessage());
	}

}