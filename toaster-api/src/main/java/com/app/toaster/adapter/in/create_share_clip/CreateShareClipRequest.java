package com.app.toaster.adapter.in.create_share_clip;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateShareClipRequest(
    @NotNull(message = "공유클립은 null일 수 없습니다.")
    @NotBlank(message = "공유클립 제목은 비어있으면 안됩니다." )
    String title
) {
}
