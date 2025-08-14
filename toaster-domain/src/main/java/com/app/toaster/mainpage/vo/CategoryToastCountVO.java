package com.app.toaster.mainpage.vo;

public record CategoryToastCountVO(
    Long categoryId,
    String categoryTitle,
    Long toastCount
) {
}
