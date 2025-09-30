package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.BadRequestException;
import com.app.toaster.exception.model.CustomException;
import com.app.toaster.toast.enums.ClipType;
import com.app.toaster.util.LongListToJsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

	@Column(columnDefinition = "json")
	@Convert(converter = LongListToJsonConverter.class)
	private List<Long> members;

	@Builder
	public ClipEntity(String title, Long ownerId, int priority, ClipType clipType) {
		this.title = title;
		this.ownerId = ownerId;
		this.members = null;
		this.priority = priority;
		this.type = clipType;
	}

	public void changePrivate() {
		if (this.type.equals(ClipType.PRIVATE) && !isEmptyMemberList()) { //주인만 있지않은 멤버 리스트는 private으로 못만들게
			throw new BadRequestException(Error.BAD_REQUEST_VISIBILITY_CLIP, Error.BAD_REQUEST_VISIBILITY_CLIP.getMessage());
		}
		this.type = ClipType.PRIVATE;
		this.members = makeOnlyOwnerState();
	}

	public void changeShared() {
		if (this.type.equals(ClipType.SHARED)) {
			throw new BadRequestException(Error.BAD_REQUEST_VISIBILITY_CLIP, Error.BAD_REQUEST_VISIBILITY_CLIP.getMessage());
		}
		this.type = ClipType.SHARED;
		this.members = makeOnlyOwnerState();
	}

	public void addMember(Long newMemberId) {
		List<Long> newMembers = new java.util.ArrayList<>(this.members);
		newMembers.add(newMemberId);
		this.members = newMembers;
	}

	public void exitMember(Long exitMemberId) {
		List<Long> newMembers = new java.util.ArrayList<>(this.members);
		newMembers.remove(exitMemberId);
		this.members = newMembers;
	}

	private List<Long> makeOnlyOwnerState() {
		return List.of(ownerId);
	}

	private boolean isEmptyMemberList() {
		if (members.equals(makeOnlyOwnerState())) {
			return true;
		}
		return false;
	}

	public boolean isUserInClipMembers(Long userId) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Long> memberList = objectMapper.convertValue(this.members, List.class);
		if (memberList.contains(userId)) {
			return true;
		}
		return false;
	}

	public void editTitle(String title) {
		if (title == null || title.isBlank()) {
			throw new CustomException(Error.BAD_REQUEST_VALIDATION, Error.BAD_REQUEST_VALIDATION.getMessage());
		}
		this.title = title;
	}

	/**
	 * TODO: 생각해볼점.
	 * 공유클립을 생성했을 때 기존 클립로직은 다 영향 받을듯.
	 * 1. 해당 클립 수정 -> 기존 클립 수정 api등도 고려해야함. -> 수정 이벤트 히스토리 테이블에 저장. 참여자와 소유자 모두 수정가능.
	 * 2. 해당 클립 삭제 -> 만약 참가자들이 존재할경우 -> 삭제할수없다. 참가자들이 없을경우 삭제. 이때 softdelete가 목표힘들면 기존 로직 냅두기. 소유자가 아닌사람은 삭제권한 x
	 * 3. 클립생성 -> 기존 클립을 생성 + 공유클립테이블에도 생성.
	 * 4. 클립조회 -> 공유클립 조회할 때 만약 내가 참여한 클립을 띄워야하는데, 이는 인덱스처리할 테이블로 해서 기록해야될듯. 아니면 유저에 공유클립 id리스트를 넣어둘까?
	 * 5. 클립 나가기 -> 공유클립에서
	 */
}
