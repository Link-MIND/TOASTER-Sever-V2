package com.app.toaster.page.domain.vo;

import java.util.List;

public record MainPageVO(
    String nickname,
    int totalToastCount,
    int readToastCount,
    List<CategoryToastCountVO> categories
) {
}
