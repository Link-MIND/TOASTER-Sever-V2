package com.app.toaster.application.port.load_mainpage.out;

import com.app.toaster.mainpage.model.MainPage;

public interface LoadMainPagePort {
    MainPage loadMainPageData(Long userId);
}
