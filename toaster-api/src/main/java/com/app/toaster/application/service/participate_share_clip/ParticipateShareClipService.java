package com.app.toaster.application.service.participate_share_clip;

import com.app.toaster.application.port.load_user_setting.out.LoadUserPort;
import com.app.toaster.application.port.participate_clip.in.ParticipateClipCommand;
import com.app.toaster.application.port.participate_clip.in.ParticipateClipUseCase;
import com.app.toaster.application.port.participate_clip.out.ParticipateShareClipPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipateShareClipService implements ParticipateClipUseCase {

    private final ParticipateShareClipPort participateShareClipPort;
    private final LoadUserPort loadUserPort;
    @Override
    public boolean participateShareClip(ParticipateClipCommand command) {
        if (loadUserPort.findBySocialId(command.socialId()) != null){
            return participateShareClipPort.participateClipMember(command.socialId());
        }
        boolean migrateResult = participateShareClipPort.migrateClipAndToasts(command.socialId());
        if (!migrateResult){
            throw new CustomException(Error.UNPROCESSABLE_V1_SERVER_EXCEPTION, Error.UNPROCESSABLE_V1_SERVER_EXCEPTION.getMessage());
        }
        return true;
    }
}
