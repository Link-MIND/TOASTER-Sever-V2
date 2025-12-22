package com.app.toaster.log_history.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdateHistoryRepository extends JpaRepository<UpdateHistory, Long> {
}
