package com.app.toaster.application.port.make_unique_key.in.command;

public record UpdateUniqueKeyCommand(
    String uniqueKey,
    Long userId
) {
    public static UpdateUniqueKeyCommand ofCommand(String uniqueKey, Long userId){
        return new UpdateUniqueKeyCommand(uniqueKey,userId);
    }
}
