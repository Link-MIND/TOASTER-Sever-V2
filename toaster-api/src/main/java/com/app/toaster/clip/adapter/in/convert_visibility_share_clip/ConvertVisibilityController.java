package com.app.toaster.clip.adapter.in.convert_visibility_share_clip;

import com.app.toaster.clip.application.port.in.ConvertShareClipVisibilityUseCase;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.toast.domain.toast.enums.ClipType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/share-clip")
class ConvertVisibilityController {

    private final ConvertShareClipVisibilityUseCase useCase;

    @PatchMapping("/{userId}/{clipId}")
    ApiResponse<?> convertVisibilityShareClip(@PathVariable(value = "userId") Long userId, @PathVariable(value = "clipId") Long clipId, @RequestParam String wantTo){
        ClipType desiredClipType = ClipType.valueOf(wantTo.trim().toUpperCase());
        if (desiredClipType.equals(ClipType.PRIVATE)){
            return ApiResponse.success(Success.UPDATE_VISIBILITY_SHARE_CLIP_SUCCESS, useCase.changeShareClipToPrivateClip(ChangeClipVisibilityCommand.toCommand(userId, clipId)));
        }
        return ApiResponse.success(Success.UPDATE_VISIBILITY_SHARE_CLIP_SUCCESS, useCase.changePrivateClipToShareClip(ChangeClipVisibilityCommand.toCommand(userId, clipId)));
    }

}
