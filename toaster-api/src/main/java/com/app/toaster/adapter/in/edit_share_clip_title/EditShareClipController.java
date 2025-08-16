package com.app.toaster.adapter.in.edit_share_clip_title;

import com.app.toaster.application.port.edit_share_clip_title.in.EditShareClipTitleCommand;
import com.app.toaster.application.port.edit_share_clip_title.in.EditShareClipTitleUseCase;
import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/share-clip")
class EditShareClipController {

    private final EditShareClipTitleUseCase editShareClipTitleUseCase;

    @PatchMapping("/{clipId}")
    ApiResponse<EditShareClipResponseDto> editShareClipTitle(@UserId Long userid, @PathVariable Long clipId, @RequestBody EditShareClipRequestDto editShareClipRequestDto){
        return ApiResponse.success(Success.UPDATE_SHARE_CLIP_TITLE_SUCCESS, editShareClipTitleUseCase.editClipTitle(EditShareClipTitleCommand.toCommand(userid,clipId,editShareClipRequestDto)));

    }
}
