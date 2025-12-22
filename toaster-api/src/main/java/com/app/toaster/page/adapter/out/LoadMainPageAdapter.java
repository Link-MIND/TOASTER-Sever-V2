package com.app.toaster.page.adapter.out;

import com.app.toaster.page.domain.model.MainPage;
import com.app.toaster.page.domain.vo.MainPageVO;
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
