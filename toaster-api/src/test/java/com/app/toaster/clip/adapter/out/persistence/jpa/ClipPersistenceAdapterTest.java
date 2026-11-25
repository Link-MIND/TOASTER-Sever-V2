package com.app.toaster.clip.adapter.out.persistence.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.app.toaster.toast.domain.toast.enums.ClipType;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClipPersistenceAdapterTest {

	@Mock
	private ClipRepository clipRepository;

	@Mock
	private ClipMapper clipMapper;

	@InjectMocks
	private ClipPersistenceAdapter adapter;

	@Test
	void checkClipPermission_ownerAlwaysAllowed() {
		ClipEntity entity = ClipEntity.builder()
			.title("clip")
			.ownerId(10L)
			.priority(1)
			.clipType(ClipType.PRIVATE)
			.build();

		when(clipRepository.findById(1L)).thenReturn(Optional.of(entity));

		assertThat(adapter.checkClipPermission(1L, 10L)).isTrue();
	}

	@Test
	void checkClipPermission_privateClip_nonOwnerDenied() {
		ClipEntity entity = ClipEntity.builder()
			.title("clip")
			.ownerId(10L)
			.priority(1)
			.clipType(ClipType.PRIVATE)
			.build();

		when(clipRepository.findById(1L)).thenReturn(Optional.of(entity));

		assertThat(adapter.checkClipPermission(1L, 20L)).isFalse();
	}

	@Test
	void checkClipPermission_sharedClip_memberAllowed() {
		ClipEntity entity = ClipEntity.builder()
			.title("clip")
			.ownerId(10L)
			.priority(1)
			.clipType(ClipType.SHARED)
			.build();
		entity.addMember(20L);

		when(clipRepository.findById(1L)).thenReturn(Optional.of(entity));

		assertThat(adapter.checkClipPermission(1L, 20L)).isTrue();
	}

	@Test
	void checkClipPermission_sharedClip_nonMemberDenied() throws Exception {
		ClipEntity entity = ClipEntity.builder()
			.title("clip")
			.ownerId(10L)
			.priority(1)
			.clipType(ClipType.SHARED)
			.build();

		// Legacy data can theoretically have null members; ensure it is treated as "no membership".
		var membersField = ClipEntity.class.getDeclaredField("members");
		membersField.setAccessible(true);
		membersField.set(entity, null);

		when(clipRepository.findById(1L)).thenReturn(Optional.of(entity));

		assertThat(adapter.checkClipPermission(1L, 20L)).isFalse();
	}
}
