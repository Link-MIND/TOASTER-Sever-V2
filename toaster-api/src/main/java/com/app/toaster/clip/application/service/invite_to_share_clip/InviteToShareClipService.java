package com.app.toaster.clip.application.service.invite_to_share_clip;

import com.app.toaster.clip.application.port.in.InviteToShareClipUseCase;
import com.app.toaster.clip.application.port.out.CheckShareClipOwnerPort;
import com.app.toaster.clip.application.port.out.InviteToShareClipPort;
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

    @Override
    @Transactional
    public void invite(Long userId, Long clipId, Long inviteeId) {
        checkShareClipOwnerPort.check(clipId, userId);
        findUserPort.findById(inviteeId);
        inviteToShareClipPort.addMember(clipId, inviteeId);
    }
}