package com.app.toaster.application.service.exit_share_clip;

import com.app.toaster.adapter.in.exit_share_clip_member.ExitShareClipCommand;
import com.app.toaster.application.port.common.CheckClipMemberPort;
import com.app.toaster.application.port.common.CheckClipOwnerPort;
import com.app.toaster.application.port.exit_share_clip.in.ExitShareClipResponseDto;
import com.app.toaster.application.port.exit_share_clip.in.ExitShareClipUseCase;
import com.app.toaster.application.port.exit_share_clip.out.ExitShareClipPort;
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
        if (checkClipOwnerPort.existsByIdAndUserId(command.clipId(), command.userId())) {
            exitShareClipPort.deleteByOwner(command.clipId(), command.userId());
        }
        if (!checkClipMemberPort.checkUserInClipMember(command.userId(), command.clipId())) {
            return ExitShareClipResponseDto.fail(command.userId(), command.clipId());
        }
        exitShareClipPort.exitShareClip(command.userId(), command.clipId());

        return ExitShareClipResponseDto.success(command.userId(), command.clipId());
    }
}
