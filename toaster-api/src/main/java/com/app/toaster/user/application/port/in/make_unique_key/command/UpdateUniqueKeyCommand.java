package com.app.toaster.user.application.port.in.make_unique_key.command;

public record UpdateUniqueKeyCommand(
    String uniqueKey,
    Long userId
) {
    public static UpdateUniqueKeyCommand ofCommand(String uniqueKey, Long userId){
        return new UpdateUniqueKeyCommand(uniqueKey,userId);
    }
}
