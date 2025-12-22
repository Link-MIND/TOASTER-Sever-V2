package com.app.toaster.clip.adapter.in.invite_to_share_clip;

import jakarta.validation.constraints.NotNull;

public record InviteToShareClipRequest(
    @NotNull Long clipId,
    @NotNull Long inviteeId
) {
}
