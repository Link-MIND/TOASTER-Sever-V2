package com.app.toaster.out.persistence.clip;

import java.time.LocalDateTime;

import com.app.toaster.toast.enums.ClipType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "clip")
class ClipEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Column(nullable = false)
	private Long ownerId;

	private int priority;

	@Column(nullable = false)
	private ClipType type;

	private String members;
	@Builder
	public ClipEntity(String title, Long ownerId, int priority) {
		this.title = title;
		this.ownerId = ownerId;
		this.members = null;
		this.priority = priority;
	}
}
