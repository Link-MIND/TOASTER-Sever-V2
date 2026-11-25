package com.app.toaster.clip.application.service.invite_to_share_clip;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.app.toaster.clip.application.port.out.CheckShareClipOwnerPort;
import com.app.toaster.clip.application.port.out.InviteToShareClipPort;
import com.app.toaster.clip.application.port.out.LoadSharedClipPort;
import com.app.toaster.clip.domain.model.Clip;
import com.app.toaster.exception.model.BadRequestException;
import com.app.toaster.exception.model.NotFoundException;
import com.app.toaster.toast.domain.toast.enums.ClipType;
import com.app.toaster.user.application.port.out.FindUserPort;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InviteToShareClipServiceTest {

	@Mock
	private InviteToShareClipPort inviteToShareClipPort;

	@Mock
	private FindUserPort findUserPort;

	@Mock
	private CheckShareClipOwnerPort checkShareClipOwnerPort;

	@Mock
	private LoadSharedClipPort loadSharedClipPort;

	@InjectMocks
	private InviteToShareClipService service;

	@Test
	void invite_throwsNotFound_whenClipMissing() {
		doNothing().when(checkShareClipOwnerPort).check(100L, 10L);
		when(loadSharedClipPort.findClipById(100L)).thenReturn(null);

		assertThatThrownBy(() -> service.invite(10L, 100L, 20L))
			.isInstanceOf(NotFoundException.class);
	}

	@Test
	void invite_throwsBadRequest_whenClipNotShared() {
		doNothing().when(checkShareClipOwnerPort).check(100L, 10L);
		when(loadSharedClipPort.findClipById(100L)).thenReturn(
			Clip.builder().id(100L).ownerId(10L).type(ClipType.PRIVATE).members(List.of(10L)).title("t").priority(1).build()
		);

		assertThatThrownBy(() -> service.invite(10L, 100L, 20L))
			.isInstanceOf(BadRequestException.class);
	}

	@Test
	void invite_addsMember_whenSharedClipAndInviteeExists() {
		doNothing().when(checkShareClipOwnerPort).check(100L, 10L);
		when(loadSharedClipPort.findClipById(100L)).thenReturn(
			Clip.builder().id(100L).ownerId(10L).type(ClipType.SHARED).members(List.of(10L)).title("t").priority(1).build()
		);
		when(findUserPort.findById(20L)).thenReturn(null);

		service.invite(10L, 100L, 20L);

		verify(inviteToShareClipPort).addMember(100L, 20L);
	}
}

