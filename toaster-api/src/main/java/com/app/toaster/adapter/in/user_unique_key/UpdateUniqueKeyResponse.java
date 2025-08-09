package com.app.toaster.adapter.in.user_unique_key;

public record UpdateUniqueKeyResponse(
    String result
) {
    public static UpdateUniqueKeyResponse fail(){
        return new UpdateUniqueKeyResponse("N");
    }
    public static UpdateUniqueKeyResponse success(){
        return new UpdateUniqueKeyResponse("Y");
    }
}
