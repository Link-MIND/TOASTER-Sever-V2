package com.app.toaster.page.adapter.in.load_mainpage;

import com.app.toaster.page.domain.vo.CategoryToastCountVO;

import java.util.List;

public record LoadMainPageResponse(
    String nickname,
    int readToastNum,
    int allToastNum,
    List<CategoryToastCountVO> mainCategoryListDto) {
    public static LoadMainPageResponse toResponse(String nickname, int readToastNum, int allToastNum, List<CategoryToastCountVO> mainCategoryListDto) {
        return new LoadMainPageResponse(
            nickname,
            readToastNum,
            allToastNum,
            mainCategoryListDto
        );
    }
}
