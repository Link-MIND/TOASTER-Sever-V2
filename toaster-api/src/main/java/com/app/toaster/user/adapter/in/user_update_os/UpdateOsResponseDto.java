package com.app.toaster.user.adapter.in.user_update_os;

public record UpdateOsResponseDto(
    String result
) {
    public static UpdateOsResponseDto fail(){
        return new UpdateOsResponseDto("N");
    }
    public static UpdateOsResponseDto success(){
        return new UpdateOsResponseDto("Y");
    }

}
