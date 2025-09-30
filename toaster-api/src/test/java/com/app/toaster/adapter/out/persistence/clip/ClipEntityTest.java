package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.toast.enums.ClipType;
import com.app.toaster.util.LongListToJsonConverter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClipEntityTest {

	@Autowired
	private ClipRepository clipRepository;

	@Test
	@DisplayName("멤버 추가 및 삭제 시 JSON 컨버터 정상 작동")
	void testAddAndRemoveMembers() {
		// given
		ClipEntity clip = ClipEntity.builder()
			.title("테스트 클립")
			.ownerId(1L)
			.priority(1)
			.clipType(ClipType.PRIVATE)
			.build();
		clip.changeShared();
		ClipEntity savedClip = clipRepository.save(clip);


		// when: 멤버 추가
		savedClip.addMember(2L);
		savedClip.addMember(3L);
		clipRepository.saveAndFlush(savedClip);

		// then: DB에 반영되었는지 확인
		ClipEntity found = clipRepository.findById(savedClip.getId()).orElseThrow();
		assertThat(found.getMembers()).containsExactlyInAnyOrder(1L, 2L, 3L);

		// when: 멤버 제거
		found.exitMember(2L);
		clipRepository.saveAndFlush(found);

		// then: 멤버 삭제 반영 확인
		ClipEntity updated = clipRepository.findById(savedClip.getId()).orElseThrow();
		assertThat(updated.getMembers()).containsExactlyInAnyOrder(1L, 3L);
	}

	@Test
	void testJsonConverter() {
		LongListToJsonConverter converter = new LongListToJsonConverter();
		List<Long> members = List.of(1L, 2L, 3L);

		String json = converter.convertToDatabaseColumn(members);
		List<Long> result = converter.convertToEntityAttribute(json);

		assertThat(result).containsExactly(1L, 2L, 3L);
	}
}