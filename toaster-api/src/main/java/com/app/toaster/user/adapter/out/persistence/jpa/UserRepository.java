package com.app.toaster.user.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserEntity, Long> {
}
