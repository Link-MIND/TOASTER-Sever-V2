package com.app.toaster.user.adapter.out.persistence.jpa;

import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.NotFoundException;
import com.app.toaster.user.application.port.out.UpdateUniqueKeyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UpdateUniqueKeyPersistenceAdapter implements UpdateUniqueKeyPort {

    private final UserRepository userRepository;
    @Override
    public void updateUniqueKey(String uniqueKey, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
            () -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        user.updateUniqueKey(uniqueKey);
    }
}
