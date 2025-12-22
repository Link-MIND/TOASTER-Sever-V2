package com.app.toaster.page.adapter.out;

import com.app.toaster.page.domain.vo.MainPageVO;

import java.util.Optional;

public interface MainPageQueryRepository {
    Optional<MainPageVO> getMainPageData(Long userId);
}
