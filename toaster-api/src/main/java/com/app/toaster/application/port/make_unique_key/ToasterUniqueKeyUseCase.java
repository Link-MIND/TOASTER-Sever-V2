package com.app.toaster.application.port.make_unique_key;

import com.app.toaster.adapter.in.user_unique_key.MakeUniqueKeyResponse;
import com.app.toaster.adapter.in.user_unique_key.UpdateUniqueKeyResponse;
import com.app.toaster.application.port.make_unique_key.in.command.UpdateUniqueKeyCommand;

public interface ToasterUniqueKeyUseCase {
    MakeUniqueKeyResponse makeUniqueKey();
    UpdateUniqueKeyResponse updateUniqueKey(UpdateUniqueKeyCommand command);
}
