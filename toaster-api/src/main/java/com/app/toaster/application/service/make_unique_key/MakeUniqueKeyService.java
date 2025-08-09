package com.app.toaster.application.service.make_unique_key;

import com.app.toaster.adapter.in.user_unique_key.MakeUniqueKeyResponse;
import com.app.toaster.adapter.in.user_unique_key.UpdateUniqueKeyResponse;
import com.app.toaster.application.port.make_unique_key.ToasterUniqueKeyUseCase;
import com.app.toaster.application.port.make_unique_key.in.command.UpdateUniqueKeyCommand;
import com.app.toaster.application.port.make_unique_key.out.UpdateUniqueKeyPort;
import com.app.toaster.exception.model.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MakeUniqueKeyService implements ToasterUniqueKeyUseCase {

    private final UpdateUniqueKeyPort updateUniqueKeyPort;

    @Override
    public MakeUniqueKeyResponse makeUniqueKey() {
        return MakeUniqueKeyResponse.success(makeUniqueKeyString());
    }

    private String makeUniqueKeyString(){
        return "TOASTER" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    @Transactional
    public UpdateUniqueKeyResponse updateUniqueKey(UpdateUniqueKeyCommand command) {
        try {
            updateUniqueKeyPort.updateUniqueKey(command.uniqueKey(), command.userId());
            return UpdateUniqueKeyResponse.success();
        }catch (BadRequestException e){
            log.info("[UPDATEUNIQUEKEY ERROR] uniqueKey가 null입니다.");
            return UpdateUniqueKeyResponse.fail();
        }catch (Exception e){
            log.info("[UPDATEUNIQUEKEY ERROR] uniqueKey가 업데이트 중 예기치 못한 에러 발생, userid = {}", command.userId());
            return UpdateUniqueKeyResponse.fail();
        }
    }
}
