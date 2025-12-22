package com.app.toaster.clip.application.port.in;

import com.app.toaster.clip.adapter.in.exit_share_clip_member.ExitShareClipCommand;
import com.app.toaster.clip.adapter.in.exit_share_clip_member.ExitShareClipResponseDto;

public interface ExitShareClipUseCase {
    ExitShareClipResponseDto exitShareClip(ExitShareClipCommand command);
}
