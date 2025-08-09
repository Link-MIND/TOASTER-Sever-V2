package com.app.toaster.application.port.user_update_os.in;

import com.app.toaster.adapter.in.user_update_os.UpdateOsResponseDto;

public interface UpdateOsUseCase {
    UpdateOsResponseDto updateOs(UpdateOsCommand command);
}
