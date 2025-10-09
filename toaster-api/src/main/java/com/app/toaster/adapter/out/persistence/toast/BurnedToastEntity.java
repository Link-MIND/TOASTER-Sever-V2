package com.app.toaster.adapter.out.persistence.toast;

import com.app.toaster.burned_toast.enums.NotificationStatus;
import com.app.toaster.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "burnedToast")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class BurnedToastEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate burnedAt;

	@Column(nullable = false)
	private Long toastId;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private NotificationStatus notificationStatus;

	//todo 팝업매니저 id 추가

	@Builder
	public BurnedToastEntity(LocalDate burnedAt, Long toastId, NotificationStatus notificationStatus) {
		this.burnedAt = burnedAt;
		this.toastId = toastId;
		this.notificationStatus = notificationStatus;
	}
}
