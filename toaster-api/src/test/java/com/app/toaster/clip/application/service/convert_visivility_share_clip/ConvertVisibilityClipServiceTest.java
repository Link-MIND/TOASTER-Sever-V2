package com.app.toaster.clip.application.service.convert_visivility_share_clip;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.app.toaster.clip.adapter.in.convert_visibility_share_clip.ChangeClipVisibilityCommand;
import com.app.toaster.clip.application.port.out.ConvertShareClipPort;
import com.app.toaster.clip.domain.model.Clip;
import com.app.toaster.exception.model.ForbiddenException;
import com.app.toaster.global.port.CheckClipOwnerPort;
import com.app.toaster.toast.domain.toast.enums.ClipType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConvertVisibilityClipServiceTest {

	@Mock
	private ConvertShareClipPort convertShareClipPort;

	@Mock
	private CheckClipOwnerPort checkClipOwnerPort;

	@InjectMocks
	private ConvertVisibilityClipService service;

	@Test
	void changePrivateClipToShareClip_forbiddenWhenNotOwner() {
		when(checkClipOwnerPort.existsByIdAndUserId(100L, 10L)).thenReturn(false);

		assertThatThrownBy(() -> service.changePrivateClipToShareClip(ChangeClipVisibilityCommand.toCommand(10L, 100L)))
			.isInstanceOf(ForbiddenException.class);
	}

	@Test
	void changeShareClipToPrivateClip_forbiddenWhenNotOwner() {
		when(checkClipOwnerPort.existsByIdAndUserId(100L, 10L)).thenReturn(false);

		assertThatThrownBy(() -> service.changeShareClipToPrivateClip(ChangeClipVisibilityCommand.toCommand(10L, 100L)))
			.isInstanceOf(ForbiddenException.class);
	}

	@Test
	void changePrivateClipToShareClip_ownerAllowed_callsPort() {
		when(checkClipOwnerPort.existsByIdAndUserId(100L, 10L)).thenReturn(true);
		when(convertShareClipPort.convertPrivateClipToShare(10L, 100L)).thenReturn(
			Clip.builder().id(100L).ownerId(10L).type(ClipType.SHARED).members(List.of(10L)).title("t").priority(1).build()
		);

		service.changePrivateClipToShareClip(ChangeClipVisibilityCommand.toCommand(10L, 100L));

		verify(convertShareClipPort).convertPrivateClipToShare(10L, 100L);
	}

	@Test
	void changeShareClipToPrivateClip_ownerAllowed_callsPort() {
		when(checkClipOwnerPort.existsByIdAndUserId(100L, 10L)).thenReturn(true);
		when(convertShareClipPort.convertShareClipToPrivate(10L, 100L)).thenReturn(
			Clip.builder().id(100L).ownerId(10L).type(ClipType.PRIVATE).members(List.of(10L)).title("t").priority(1).build()
		);

		service.changeShareClipToPrivateClip(ChangeClipVisibilityCommand.toCommand(10L, 100L));

		verify(convertShareClipPort).convertShareClipToPrivate(10L, 100L);
	}
}

