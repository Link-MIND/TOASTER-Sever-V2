package com.app.toaster.application.service.convert_visivility_share_clip;

import com.app.toaster.adapter.in.convert_visibility_share_clip.ChangeClipVisibilityResponse;
import com.app.toaster.application.port.convert_share_clip.in.ChangeClipVisibilityCommand;
import com.app.toaster.application.port.convert_share_clip.in.ConvertShareClipVisibilityUseCase;
import com.app.toaster.application.port.convert_share_clip.out.ConvertShareClipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConvertVisibilityClipService implements ConvertShareClipVisibilityUseCase {
    private final ConvertShareClipPort convertShareClipPort;
    @Override
    @Transactional
    public ChangeClipVisibilityResponse changePrivateClipToShareClip(ChangeClipVisibilityCommand command) {
        return ChangeClipVisibilityResponse.toResponse(convertShareClipPort.convertPrivateClipToShare(command.userId(), command.clipId()));
    }

    @Override
    @Transactional
    public ChangeClipVisibilityResponse changeShareClipToPrivateClip(ChangeClipVisibilityCommand command) {
        return ChangeClipVisibilityResponse.toResponse(convertShareClipPort.convertShareClipToPrivate(command.userId(), command.clipId()));
    }
}
