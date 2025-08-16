package com.app.toaster.application.port.edit_share_clip_title.in;

import com.app.toaster.adapter.in.edit_share_clip_title.EditShareClipResponseDto;

public interface EditShareClipTitleUseCase {
    EditShareClipResponseDto editClipTitle(EditShareClipTitleCommand command);
}
