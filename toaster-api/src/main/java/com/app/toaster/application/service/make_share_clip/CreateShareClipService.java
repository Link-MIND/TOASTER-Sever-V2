package com.app.toaster.application.service.make_share_clip;

import com.app.toaster.adapter.in.create_share_clip.CreateShareClipResponse;
import com.app.toaster.application.port.create_share_clip.in.CreateShareClipCommand;
import com.app.toaster.application.port.create_share_clip.in.CreateShareClipUseCase;
import com.app.toaster.application.port.create_share_clip.out.CreateShareClipPort;
import com.app.toaster.clip.model.Clip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class CreateShareClipService implements CreateShareClipUseCase {

    private final CreateShareClipPort createShareClipPort;
    @Override
    @Transactional
    public CreateShareClipResponse createShareClip(CreateShareClipCommand command) {
        Clip newClip = createShareClipPort.createShareClip(command.userId(), command.title());
        return CreateShareClipResponse.toResponse(newClip);
    }
}
