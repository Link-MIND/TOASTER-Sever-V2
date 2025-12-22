package com.app.toaster.user.application.service.user_update_os;

import com.app.toaster.exception.model.BadRequestException;
import com.app.toaster.user.adapter.in.user_update_os.UpdateOsResponseDto;
import com.app.toaster.user.application.port.in.user_update_os.UpdateOsCommand;
import com.app.toaster.user.application.port.in.user_update_os.UpdateOsUseCase;
import com.app.toaster.user.application.port.out.UpdateOsPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserUpdateOsService implements UpdateOsUseCase {

    private final UpdateOsPort updateOsPort;
    @Override
    @Transactional
    public UpdateOsResponseDto updateOs(UpdateOsCommand command) {
        try {
            updateOsPort.updateOs(command.userId(), command.os());
            return UpdateOsResponseDto.success();
        }catch (BadRequestException e){
            log.info("[UPDATEOS ERROR] os가 null입니다.");
            return UpdateOsResponseDto.fail();
        }catch (Exception e){
            log.info("[UPDATEOS ERROR] os 업데이트 중 예기치 못한 에러 발생, userid = {}",command.userId());
            return UpdateOsResponseDto.fail();
        }
    }
}
