package com.app.toaster.clip.application.port.in;

import com.app.toaster.clip.adapter.in.create_share_clip.CreateShareClipCommand;
import com.app.toaster.clip.adapter.in.create_share_clip.CreateShareClipResponse;

public interface CreateShareClipUseCase {
    CreateShareClipResponse createShareClip(CreateShareClipCommand command);
}
