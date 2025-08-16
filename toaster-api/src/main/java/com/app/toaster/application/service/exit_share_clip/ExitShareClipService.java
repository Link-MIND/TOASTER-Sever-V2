package com.app.toaster.application.service.exit_share_clip;

import com.app.toaster.adapter.in.exit_share_clip_member.ExitShareClipCommand;
import com.app.toaster.application.port.common.CheckClipMemberPort;
import com.app.toaster.application.port.common.CheckClipOwnerPort;
import com.app.toaster.application.port.exit_share_clip.in.ExitShareClipResponseDto;
import com.app.toaster.application.port.exit_share_clip.in.ExitShareClipUseCase;
import com.app.toaster.application.port.exit_share_clip.out.ExitShareClipPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExitShareClipService implements ExitShareClipUseCase {

    private final ExitShareClipPort exitShareClipPort;
    private final CheckClipOwnerPort checkClipOwnerPort;
    private final CheckClipMemberPort checkClipMemberPort;

    @Override
    @Transactional
    public ExitShareClipResponseDto exitShareClip(ExitShareClipCommand command) {
        //TODO: 자신이 주인인데, 나가려고하는지 valid -> 다른 에러코드를 던져줘서 클라가 클립삭제 api를 호출하게 분기처리 현재는 임시로 400
        //TODO: 자신이 멤버인지 valid
        if (checkClipOwnerPort.existsByIdAndUserId(command.clipId(), command.userId())) {
            throw new BadRequestException(Error.BAD_REQUEST_ID, Error.BAD_REQUEST_ID.getMessage());
        }
        if (!checkClipMemberPort.checkUserInClipMember(command.userId(), command.clipId())) {
            return ExitShareClipResponseDto.fail(command.userId(), command.clipId());
        }
        exitShareClipPort.exitShareClip(command.userId(), command.clipId());

        return ExitShareClipResponseDto.success(command.userId(), command.clipId());
    }
}
