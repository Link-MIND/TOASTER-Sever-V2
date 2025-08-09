package com.app.toaster.adapter.in.user_update_os;

import jakarta.validation.constraints.NotBlank;

public record UpdateOsRequestDto(
    @NotBlank(message = "OS가 비어있으면 안됩니다. ANDROID, IOS")
    String os
) {
}
