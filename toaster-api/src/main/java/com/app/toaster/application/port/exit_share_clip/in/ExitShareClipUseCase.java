package com.app.toaster.application.port.exit_share_clip.in;

import com.app.toaster.adapter.in.exit_share_clip_member.ExitShareClipCommand;

public interface ExitShareClipUseCase {
    ExitShareClipResponseDto exitShareClip(ExitShareClipCommand command);
}
