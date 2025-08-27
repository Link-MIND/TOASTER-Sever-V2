package com.app.toaster.adapter.in.edit_share_clip_title;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditShareClipRequestDto(
    @NotNull(message = "제목은 null일 수 없습니다.")
    @NotBlank(message = "제목은 비어있을 수 없습니다.")
    String title
) {
}
