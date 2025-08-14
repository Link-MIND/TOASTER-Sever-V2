package com.app.toaster.adapter.out.persistence.user;

import com.app.toaster.application.port.create_toast.out.FindClipOwnerPort;
import com.app.toaster.application.port.user_allow_push.out.UpdateAllowPushPort;
import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.NotFoundException;
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