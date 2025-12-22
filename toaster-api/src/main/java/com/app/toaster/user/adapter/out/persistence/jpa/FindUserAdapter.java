package com.app.toaster.user.adapter.out.persistence.jpa;

import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.NotFoundException;
import com.app.toaster.user.application.port.out.FindUserPort;
import com.app.toaster.user.domain.models.ToasterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserAdapter implements FindUserPort {
    private final UserRepository userRepository;

    @Override
    public ToasterUser findById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        return UserMapper.toDomain(userEntity);
    }
}
