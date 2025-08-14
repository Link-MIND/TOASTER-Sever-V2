package com.app.toaster.adapter.in.user_unique_key;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUniqueKeyRequest(
    @NotBlank(message = "uniqueKey는 비어있으면 안됩니다.")
    @NotNull(message = "uniqueKey는 null이면 안됩니다.")
    String uniqueKey
) {
}
