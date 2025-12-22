package com.app.toaster.clip.adapter.in.invite_to_share_clip;

import com.app.toaster.clip.application.port.in.InviteToShareClipUseCase;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/share-clip")
public class InviteToShareClipController {

    private final InviteToShareClipUseCase inviteToShareClipUseCase;

    @PostMapping("/invite/{userId}")
    public ApiResponse<?> inviteToShareClip(
        @PathVariable(value = "userId") String userId,
        @Valid @RequestBody InviteToShareClipRequest request
    ) {
        inviteToShareClipUseCase.invite(Long.parseLong(userId), request.clipId(), request.inviteeId());
        return ApiResponse.success(Success.INVITE_TO_SHARE_CLIP_SUCCESS);
    }
}
