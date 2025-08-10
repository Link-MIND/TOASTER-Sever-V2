package com.app.toaster.application.port.create_share_clip.in;

import com.app.toaster.adapter.in.create_share_clip.CreateShareClipResponse;

public interface CreateShareClipUseCase {
    CreateShareClipResponse createShareClip(CreateShareClipCommand command);
}
