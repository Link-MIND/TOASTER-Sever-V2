package com.app.toaster.user.application.port.in.make_unique_key;

import com.app.toaster.user.adapter.in.user_unique_key.MakeUniqueKeyResponse;
import com.app.toaster.user.adapter.in.user_unique_key.UpdateUniqueKeyResponse;
import com.app.toaster.user.application.port.in.make_unique_key.command.UpdateUniqueKeyCommand;

public interface ToasterUniqueKeyUseCase {
    MakeUniqueKeyResponse makeUniqueKey();
    UpdateUniqueKeyResponse updateUniqueKey(UpdateUniqueKeyCommand command);
}
