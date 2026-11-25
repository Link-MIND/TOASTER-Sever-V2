package com.app.toaster.clip.application.service.convert_visivility_share_clip;

import com.app.toaster.clip.adapter.in.convert_visibility_share_clip.ChangeClipVisibilityCommand;
import com.app.toaster.clip.adapter.in.convert_visibility_share_clip.ChangeClipVisibilityResponse;
import com.app.toaster.clip.application.port.in.ConvertShareClipVisibilityUseCase;
import com.app.toaster.clip.application.port.out.ConvertShareClipPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.ForbiddenException;
import com.app.toaster.global.port.CheckClipOwnerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConvertVisibilityClipService implements ConvertShareClipVisibilityUseCase {
    private final ConvertShareClipPort convertShareClipPort;
    private final CheckClipOwnerPort checkClipOwnerPort;

    @Override
    @Transactional
    public ChangeClipVisibilityResponse changePrivateClipToShareClip(ChangeClipVisibilityCommand command) {
        if (!checkClipOwnerPort.existsByIdAndUserId(command.clipId(), command.userId())) {
            throw new ForbiddenException(Error.UNAUTHORIZED_ACCESS, Error.UNAUTHORIZED_ACCESS.getMessage());
        }
        return ChangeClipVisibilityResponse.toResponse(convertShareClipPort.convertPrivateClipToShare(command.userId(), command.clipId()));
    }

    @Override
    @Transactional
    public ChangeClipVisibilityResponse changeShareClipToPrivateClip(ChangeClipVisibilityCommand command) {
        if (!checkClipOwnerPort.existsByIdAndUserId(command.clipId(), command.userId())) {
            throw new ForbiddenException(Error.UNAUTHORIZED_ACCESS, Error.UNAUTHORIZED_ACCESS.getMessage());
        }
        return ChangeClipVisibilityResponse.toResponse(convertShareClipPort.convertShareClipToPrivate(command.userId(), command.clipId()));
    }
}
