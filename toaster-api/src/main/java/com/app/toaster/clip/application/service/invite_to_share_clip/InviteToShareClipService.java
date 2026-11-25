package com.app.toaster.clip.application.service.invite_to_share_clip;

import com.app.toaster.clip.application.port.in.InviteToShareClipUseCase;
import com.app.toaster.clip.application.port.out.CheckShareClipOwnerPort;
import com.app.toaster.clip.application.port.out.InviteToShareClipPort;
import com.app.toaster.clip.application.port.out.LoadSharedClipPort;
import com.app.toaster.clip.domain.model.Clip;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.BadRequestException;
import com.app.toaster.exception.model.NotFoundException;
import com.app.toaster.toast.domain.toast.enums.ClipType;
import com.app.toaster.user.application.port.out.FindUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InviteToShareClipService implements InviteToShareClipUseCase {

    private final InviteToShareClipPort inviteToShareClipPort;
    private final FindUserPort findUserPort;
    private final CheckShareClipOwnerPort checkShareClipOwnerPort;
    private final LoadSharedClipPort loadSharedClipPort;

    @Override
    @Transactional
    public void invite(Long userId, Long clipId, Long inviteeId) {
        checkShareClipOwnerPort.check(clipId, userId);

        Clip clip = loadSharedClipPort.findClipById(clipId);
        if (clip == null) {
            throw new NotFoundException(Error.NOT_FOUND_CLIP_EXCEPTION, Error.NOT_FOUND_CLIP_EXCEPTION.getMessage());
        }
        if (clip.getType() != ClipType.SHARED) {
            throw new BadRequestException(Error.BAD_REQUEST_CLIP_TYPE, Error.BAD_REQUEST_CLIP_TYPE.getMessage());
        }

        findUserPort.findById(inviteeId);

        inviteToShareClipPort.addMember(clipId, inviteeId);
    }
}
