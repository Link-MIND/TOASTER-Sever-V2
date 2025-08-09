package com.app.toaster.mainpage.vo;

import java.util.List;

public record MainPageVO(
    String nickname,
    int totalToastCount,
    int readToastCount,
    List<CategoryToastCountVO> categories
) {
}
