package com.app.toaster.adapter.out.persistence.mainpage;

import com.app.toaster.mainpage.vo.MainPageVO;

import java.util.Optional;

public interface MainPageQueryRepository {
    Optional<MainPageVO> getMainPageData(Long userId);
}
