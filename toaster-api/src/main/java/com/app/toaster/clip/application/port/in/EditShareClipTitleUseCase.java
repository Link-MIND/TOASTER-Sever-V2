package com.app.toaster.clip.application.port.in;

import com.app.toaster.clip.adapter.in.edit_share_clip_title.EditShareClipResponseDto;
import com.app.toaster.clip.adapter.in.edit_share_clip_title.EditShareClipTitleCommand;

public interface EditShareClipTitleUseCase {
    EditShareClipResponseDto editClipTitle(EditShareClipTitleCommand command);
}
