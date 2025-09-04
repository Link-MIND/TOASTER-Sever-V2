package com.app.toaster.adapter.out.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findBySocialId(String socialId);
}
