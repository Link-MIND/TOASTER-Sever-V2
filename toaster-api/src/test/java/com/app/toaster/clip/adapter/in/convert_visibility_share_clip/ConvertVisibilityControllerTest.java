package com.app.toaster.clip.adapter.in.convert_visibility_share_clip;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.app.toaster.clip.application.port.in.ConvertShareClipVisibilityUseCase;
import com.app.toaster.exception.model.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ConvertVisibilityControllerTest {

	@Test
	void convertVisibilityShareClip_throwsBadRequest_whenWantToInvalid() {
		ConvertShareClipVisibilityUseCase useCase = Mockito.mock(ConvertShareClipVisibilityUseCase.class);
		ConvertVisibilityController controller = new ConvertVisibilityController(useCase);

		assertThatThrownBy(() -> controller.convertVisibilityShareClip(1L, 2L, "not-a-clip-type"))
			.isInstanceOf(BadRequestException.class);
	}
}

