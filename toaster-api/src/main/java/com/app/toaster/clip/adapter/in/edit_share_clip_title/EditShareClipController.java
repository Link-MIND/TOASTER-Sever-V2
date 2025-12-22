package com.app.toaster.clip.adapter.in.edit_share_clip_title;

import com.app.toaster.clip.application.port.in.EditShareClipTitleUseCase;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/share-clip")
class EditShareClipController {

    private final EditShareClipTitleUseCase editShareClipTitleUseCase;

    @PatchMapping("/title/{userId}/{clipId}")
    ApiResponse<EditShareClipResponseDto> editShareClipTitle(@PathVariable(value = "userId") Long userid, @PathVariable Long clipId, @RequestBody EditShareClipRequestDto editShareClipRequestDto){
        return ApiResponse.success(Success.UPDATE_SHARE_CLIP_TITLE_SUCCESS, editShareClipTitleUseCase.editClipTitle(EditShareClipTitleCommand.toCommand(userid,clipId,editShareClipRequestDto)));
    }
}
