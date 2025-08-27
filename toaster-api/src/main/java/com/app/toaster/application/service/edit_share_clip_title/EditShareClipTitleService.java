package com.app.toaster.application.service.edit_share_clip_title;

import com.app.toaster.adapter.in.edit_share_clip_title.EditShareClipResponseDto;
import com.app.toaster.application.port.edit_share_clip_title.in.EditShareClipTitleCommand;
import com.app.toaster.application.port.edit_share_clip_title.in.EditShareClipTitleUseCase;
import com.app.toaster.application.port.edit_share_clip_title.out.EditShareClipTitlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class EditShareClipTitleService implements EditShareClipTitleUseCase {
    private final EditShareClipTitlePort editShareClipTitlePort;
    @Override
    @Transactional
    public EditShareClipResponseDto editClipTitle(EditShareClipTitleCommand command) {
        //TODO: userID로 validation 추가
        try{
            editShareClipTitlePort.editShareClipTitle(command.clipId(), command.clipTitle());
        }catch (Exception e){
            return EditShareClipResponseDto.fail();
        }
        return EditShareClipResponseDto.success(command.clipId(), command.clipTitle());
    }
}
