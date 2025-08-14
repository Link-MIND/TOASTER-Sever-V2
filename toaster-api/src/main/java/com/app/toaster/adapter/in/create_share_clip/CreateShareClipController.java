package com.app.toaster.adapter.in.create_share_clip;

import com.app.toaster.application.port.create_share_clip.in.CreateShareClipCommand;
import com.app.toaster.application.port.create_share_clip.in.CreateShareClipUseCase;
import com.app.toaster.auth.UserId;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/share-clip")
class CreateShareClipController {

    /**
     * 클립 참가하기에서 생각할점
     * 링크를 누른사람의 엑세스토큰이 필요. 이때 눌렀는데, 엑세스토큰이 없는사람이면  ??
     * 엑세스토큰이 있으면 단순히 참가하면 됨.
     */
    private final CreateShareClipUseCase createShareClipUseCase;

    @PostMapping
    ApiResponse<?> createShareClip(@UserId Long userId, @RequestBody CreateShareClipRequest request) {
        return ApiResponse.success(Success.CREATE_SHARE_CLIP_SUCCESS, createShareClipUseCase.createShareClip(CreateShareClipCommand.toCommand(userId, request)));
    }

}
