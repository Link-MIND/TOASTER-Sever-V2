package com.app.toaster.application.service.load_mainpage;

import com.app.toaster.application.port.load_mainpage.in.LoadMainPageCommand;
import com.app.toaster.application.port.load_mainpage.in.LoadMainPageResponse;
import com.app.toaster.application.port.load_mainpage.in.LoadMainPageUseCase;
import com.app.toaster.application.port.load_mainpage.out.LoadMainPagePort;
import com.app.toaster.mainpage.model.MainPage;
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
