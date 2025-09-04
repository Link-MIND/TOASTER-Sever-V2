package com.app.toaster.adapter.in.participate_share_clip;

import com.app.toaster.application.port.participate_clip.in.ParticipateClipUseCase;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/share-clip")
class ParticipateClipController {

    private final ParticipateClipUseCase participateClipUseCase;
    private final

    @PostMapping("/callback")
    ApiResponse<?> shareCallbackInKakao(@RequestBody KakaoCallBackRequestDto kakaoCallBackRequestDto,
                                        @RequestHeader("Authorization") String KakaoAdminKey,
                                        @RequestHeader("X-Kakao-Resource-ID") String resourceId,
                                        @RequestHeader("User-Agent") String userAgent,
                                        @RequestHeader("Content-Type") String contentType){
        participateClipUseCase.participateShareClip(kakaoCallBackRequestDto.toCommand());
        return ApiResponse.success(Success.INVITE_SHARE_CLIP_SUCCESS, Success.INVITE_SHARE_CLIP_SUCCESS.getMessage());
    }


}
