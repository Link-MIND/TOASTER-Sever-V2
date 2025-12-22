package com.app.toaster.log_history.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "update_log")
public class UpdateHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventString; // "토스트 제목 수정"

    private String domain; // "TOAST"

    private Long domainId; // 해당 도메인의 ID (e.g., toastId)

    private Long userId;

    private LocalDateTime eventDateTime;

    @Builder
    public UpdateHistory(String eventString, String domain, Long domainId, Long userId, LocalDateTime eventDateTime) {
        this.eventString = eventString;
        this.domain = domain;
        this.domainId = domainId;
        this.userId = userId;
        this.eventDateTime = eventDateTime;
    }
}
