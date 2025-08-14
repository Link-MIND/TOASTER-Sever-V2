package com.app.toaster.adapter.out.persistence.user;

import com.app.toaster.application.port.make_unique_key.out.UpdateUniqueKeyPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.NotFoundException;
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
