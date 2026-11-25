package com.app.toaster.clip.adapter.out.persistence.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.app.toaster.exception.model.BadRequestException;
import com.app.toaster.toast.domain.toast.enums.ClipType;
import com.app.toaster.util.LongListToJsonConverter;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.jupiter.api.Test;

class ClipEntityTest {

	@Test
	void addMemberAndExitMember() {
		ClipEntity clip = ClipEntity.builder()
			.title("test-clip")
			.ownerId(1L)
			.priority(1)
			.clipType(ClipType.SHARED)
			.build();

		clip.addMember(2L);
		clip.addMember(3L);

		assertThat(clip.getMembers()).containsExactly(1L, 2L, 3L);

		clip.exitMember(2L);

		assertThat(clip.getMembers()).containsExactly(1L, 3L);
	}

	@Test
	void addMember_deduplicates() {
		ClipEntity clip = ClipEntity.builder()
			.title("test-clip")
			.ownerId(1L)
			.priority(1)
			.clipType(ClipType.SHARED)
			.build();

		clip.addMember(2L);
		clip.addMember(2L);

		assertThat(clip.getMembers()).containsExactly(1L, 2L);
	}

	@Test
	void exitMember_removesAllDuplicatesInLegacyData() throws Exception {
		ClipEntity clip = ClipEntity.builder()
			.title("test-clip")
			.ownerId(1L)
			.priority(1)
			.clipType(ClipType.SHARED)
			.build();

		Field membersField = ClipEntity.class.getDeclaredField("members");
		membersField.setAccessible(true);
		membersField.set(clip, List.of(1L, 2L, 2L, 3L));

		clip.exitMember(2L);

		assertThat(clip.getMembers()).containsExactly(1L, 3L);
	}

	@Test
	void changePrivate_rejectsSharedClipWithOtherMembers() {
		ClipEntity clip = ClipEntity.builder()
			.title("test-clip")
			.ownerId(1L)
			.priority(1)
			.clipType(ClipType.SHARED)
			.build();
		clip.addMember(2L);

		assertThatThrownBy(clip::changePrivate)
			.isInstanceOf(BadRequestException.class);
	}

	@Test
	void jsonConverter_roundTrip() {
		LongListToJsonConverter converter = new LongListToJsonConverter();
		List<Long> members = List.of(1L, 2L, 3L);

		String json = converter.convertToDatabaseColumn(members);
		List<Long> result = converter.convertToEntityAttribute(json);

		assertThat(result).containsExactly(1L, 2L, 3L);
	}
}
