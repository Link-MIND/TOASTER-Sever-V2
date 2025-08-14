package com.app.toaster.adapter.out.persistence.user;

import com.app.toaster.application.port.user_update_os.out.UpdateOsPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.NotFoundException;
import com.app.toaster.user.enums.OsType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateOsPersistenceAdapter implements UpdateOsPort {

    private final UserRepository userRepository;

    @Override
    public void updateOs(Long userId, OsType os) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
            () -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        user.updateOs(os);
    }
}
