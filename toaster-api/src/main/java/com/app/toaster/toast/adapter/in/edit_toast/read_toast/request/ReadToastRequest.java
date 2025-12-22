package com.app.toaster.toast.adapter.in.edit_toast.read_toast.request;

import com.app.toaster.toast.adapter.in.read_toast.ReadToastCommand;

public record ReadToastRequest(Boolean isRead) {

    public ReadToastCommand toCommand(Long userId, Long toastId, Boolean isRead){
        return new ReadToastCommand(userId, toastId, isRead);
    }
}
