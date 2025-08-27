package com.app.toaster.adapter.in.exit_share_clip_member;

import com.app.toaster.application.port.exit_share_clip.in.ExitShareClipResponseDto;
import com.app.toaster.application.port.exit_share_clip.in.ExitShareClipUseCase;
import com.app.toaster.auth.UserId;
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

    @DeleteMapping("/{clipId}/member")
    protected ApiResponse<ExitShareClipResponseDto> exitMemberInShareClip(@UserId Long userId, @PathVariable(name = "clipId") Long clipId){
        return ApiResponse.success(Success.EXIT_SHARE_CLIP_SUCCESS, exitShareClipUseCase.exitShareClip(ExitShareClipCommand.of(userId, clipId)));
    }
}
