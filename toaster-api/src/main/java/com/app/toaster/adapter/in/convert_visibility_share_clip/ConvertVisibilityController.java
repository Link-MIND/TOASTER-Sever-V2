package com.app.toaster.adapter.in.convert_visibility_share_clip;

import com.app.toaster.application.port.convert_share_clip.in.ChangeClipVisibilityCommand;
import com.app.toaster.application.port.convert_share_clip.in.ConvertShareClipVisibilityUseCase;
import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.toast.enums.ClipType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/share-clip")
class ConvertVisibilityController {

    private final ConvertShareClipVisibilityUseCase useCase;

    @PatchMapping
    ApiResponse<?> convertVisibilityShareClip(@UserId Long userId, @RequestParam Long clipId, @RequestParam String wantTo){
        if (ClipType.valueOf(wantTo).equals(ClipType.PRIVATE)){
            return ApiResponse.success(Success.UPDATE_VISIBILITY_SHARE_CLIP_SUCCESS, useCase.changeShareClipToPrivateClip(ChangeClipVisibilityCommand.toCommand(userId, clipId)));
        }
        return ApiResponse.success(Success.UPDATE_VISIBILITY_SHARE_CLIP_SUCCESS, useCase.changePrivateClipToShareClip(ChangeClipVisibilityCommand.toCommand(userId, clipId)));
    }

}
