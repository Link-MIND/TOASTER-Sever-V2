package com.app.toaster.out.persistence.user;

import org.springframework.stereotype.Component;

import com.app.toaster.toast.port.out.LoadUserPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadUserPort {

	private final UserRepository userRepository;

	@Override
	public boolean existsById(Long userId) {
		return userRepository.existsById(userId);
	}
}