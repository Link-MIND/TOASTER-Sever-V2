package com.app.toaster.adapter.out.persistence.burned_toast;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.app.toaster.burned_toast.enums.NotificationStatus;

@Entity
@Table(name = "burnedToast")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class BurnedToastEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate burnedAt;
	private Long toastId;
	private NotificationStatus notificationStatus;

	//todo 팝업매니저 id 추가

	@Builder
	public BurnedToastEntity(LocalDate burnedAt, Long toastId, NotificationStatus notificationStatus) {
		this.burnedAt = burnedAt;
		this.toastId = toastId;
		this.notificationStatus = notificationStatus;
	}
}
