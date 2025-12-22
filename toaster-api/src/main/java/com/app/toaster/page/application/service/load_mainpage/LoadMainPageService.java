package com.app.toaster.page.application.service.load_mainpage;

import com.app.toaster.page.adapter.in.load_mainpage.LoadMainPageCommand;
import com.app.toaster.page.adapter.in.load_mainpage.LoadMainPageResponse;
import com.app.toaster.page.adapter.in.load_mainpage.LoadMainPageUseCase;
import com.app.toaster.page.adapter.out.LoadMainPagePort;
import com.app.toaster.page.domain.model.MainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoadMainPageService implements LoadMainPageUseCase {

    private final LoadMainPagePort loadMainPagePort;
    @Override
    @Transactional(readOnly = true)
    public LoadMainPageResponse loadMainPage(LoadMainPageCommand command) {
        MainPage mainPage = loadMainPagePort.loadMainPageData(command.userId());
        return LoadMainPageResponse.toResponse(
            mainPage.getNickname(),
            mainPage.getReadToastNum(),
            mainPage.getAllToastNum(),
            mainPage.getToastcountList()
        );
    }
}
