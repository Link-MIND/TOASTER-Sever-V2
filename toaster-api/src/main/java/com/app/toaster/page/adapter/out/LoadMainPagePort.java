package com.app.toaster.page.adapter.out;

import com.app.toaster.page.domain.model.MainPage;

public interface LoadMainPagePort {
    MainPage loadMainPageData(Long userId);
}
