package com.app.toaster.application.port.load_mainpage.in;

import com.app.toaster.mainpage.vo.CategoryToastCountVO;

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
