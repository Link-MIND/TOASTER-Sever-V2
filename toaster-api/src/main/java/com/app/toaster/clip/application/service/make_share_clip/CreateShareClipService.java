package com.app.toaster.clip.application.service.make_share_clip;

import com.app.toaster.clip.adapter.in.create_share_clip.CreateShareClipCommand;
import com.app.toaster.clip.adapter.in.create_share_clip.CreateShareClipResponse;
import com.app.toaster.clip.application.port.in.CreateShareClipUseCase;
import com.app.toaster.clip.application.port.out.CreateShareClipPort;
import com.app.toaster.clip.domain.model.Clip;
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
        //TODO: title 이미 존재할 경우 못만드는거 추가
        Clip newClip = createShareClipPort.createShareClip(command.userId(), command.title());
        return CreateShareClipResponse.toResponse(newClip);
    }
}
