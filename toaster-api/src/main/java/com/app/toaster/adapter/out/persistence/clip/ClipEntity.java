package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.toast.enums.ClipType;
import jakarta.persistence.*;
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
	@Enumerated(EnumType.STRING)
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
