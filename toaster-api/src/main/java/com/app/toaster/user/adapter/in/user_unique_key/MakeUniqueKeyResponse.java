package com.app.toaster.user.adapter.in.user_unique_key;

public record MakeUniqueKeyResponse(
    String result,
    String uniqueKey
) {
    public static MakeUniqueKeyResponse success(String uniqueKey) {
        return new MakeUniqueKeyResponse("Y", uniqueKey);
    }
}

