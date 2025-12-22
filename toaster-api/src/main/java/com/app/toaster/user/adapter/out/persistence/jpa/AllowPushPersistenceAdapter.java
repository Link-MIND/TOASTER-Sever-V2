package com.app.toaster.user.adapter.out.persistence.jpa;

import com.app.toaster.user.application.port.out.UpdateAllowPushPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.NotFoundException;
import com.app.toaster.toast.adapter.out.FindClipOwnerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AllowPushPersistenceAdapter implements FindClipOwnerPort, UpdateAllowPushPort {

    private final UserRepository userRepository;

    @Override
    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public void allowPush(long userId, boolean isAllowed) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
            () -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        user.updateFcmIsAllowed(isAllowed);
    }
}