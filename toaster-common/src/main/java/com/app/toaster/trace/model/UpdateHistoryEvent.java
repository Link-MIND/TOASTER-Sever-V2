package com.app.toaster.trace.model;

public record UpdateHistoryEvent(
    String eventString,
    String domain,
    Long domainId,
    Long userId
) {
    public static UpdateHistoryEvent of(String eventString, String domain, Long domainId, Long userId) {
        return new UpdateHistoryEvent(eventString, domain, domainId, userId);
    }
}
