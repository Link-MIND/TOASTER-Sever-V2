package com.app.toaster.adapter.out.persistence.mainpage;

import com.app.toaster.application.port.load_mainpage.out.LoadMainPagePort;
import com.app.toaster.mainpage.model.MainPage;
import com.app.toaster.mainpage.vo.MainPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadMainPageAdapter implements LoadMainPagePort {

    private final MainPageQueryRepository mainPageQueryRepository;

    @Override
    public MainPage loadMainPageData(Long userId) {
        MainPageVO queryResult = mainPageQueryRepository.getMainPageData(userId)
            .orElse(null);
        assert queryResult != null;
        return MainPage.makeMainPage(
            queryResult.nickname(),
            queryResult.readToastCount(),
            queryResult.totalToastCount(),
            queryResult.categories()
        );
    }

}
