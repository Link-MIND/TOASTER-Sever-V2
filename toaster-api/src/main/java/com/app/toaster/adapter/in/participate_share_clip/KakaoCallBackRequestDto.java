package com.app.toaster.adapter.in.participate_share_clip;

import com.app.toaster.application.port.participate_clip.in.ParticipateClipCommand;

public record KakaoCallBackRequestDto(
    String ChatType,
    String HashChatId,
    Long TemplateId,
    KakaoCustomParameter kakaoCustomParameter
) {
    public ParticipateClipCommand toCommand(){
        return new ParticipateClipCommand(kakaoCustomParameter().socialId());
    }
}
