package com.app.toaster.user.application.port.in.user_update_os;

import com.app.toaster.user.adapter.in.user_update_os.UpdateOsResponseDto;

public interface UpdateOsUseCase {
    UpdateOsResponseDto updateOs(UpdateOsCommand command);
}
