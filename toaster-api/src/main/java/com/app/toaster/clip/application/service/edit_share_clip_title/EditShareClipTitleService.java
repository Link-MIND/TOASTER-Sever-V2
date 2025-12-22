package com.app.toaster.clip.application.service.edit_share_clip_title;

import com.app.toaster.clip.adapter.in.edit_share_clip_title.EditShareClipResponseDto;
import com.app.toaster.clip.adapter.in.edit_share_clip_title.EditShareClipTitleCommand;
import com.app.toaster.clip.application.port.in.EditShareClipTitleUseCase;
import com.app.toaster.clip.application.port.out.EditShareClipTitlePort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.ForbiddenException;
import com.app.toaster.global.port.CheckClipOwnerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class EditShareClipTitleService implements EditShareClipTitleUseCase {
    private final EditShareClipTitlePort editShareClipTitlePort;
    private final CheckClipOwnerPort checkClipOwnerPort;

    @Override
    @Transactional
    public EditShareClipResponseDto editClipTitle(EditShareClipTitleCommand command) {

        if (!checkClipOwnerPort.checkClipPermission(command.clipId(), command.userId())) {
            throw new ForbiddenException(Error.UNAUTHORIZED_ACCESS, Error.UNAUTHORIZED_ACCESS.getMessage());
        }

        try{
            editShareClipTitlePort.editShareClipTitle(command.clipId(), command.clipTitle());
        }catch (Exception e){
            return EditShareClipResponseDto.fail();
        }
        return EditShareClipResponseDto.success(command.clipId(), command.clipTitle());
    }
}