package com.app.toaster.clip.adapter.in.exit_share_clip_member;

import com.app.toaster.clip.application.port.in.ExitShareClipUseCase;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/share-clip")
class ExitShareClipMemberController {

    private final ExitShareClipUseCase exitShareClipUseCase;

    @DeleteMapping("/{clipId}/member/{userId}")
    protected ApiResponse<ExitShareClipResponseDto> exitMemberInShareClip(@PathVariable(value = "userId") Long exitUserId, @PathVariable(name = "clipId") Long clipId){
        return ApiResponse.success(Success.EXIT_SHARE_CLIP_SUCCESS, exitShareClipUseCase.exitShareClip(ExitShareClipCommand.of(exitUserId, clipId)));
    }
}
