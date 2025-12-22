package com.app.toaster.page.domain.vo;

public record CategoryToastCountVO(
    Long categoryId,
    String categoryTitle,
    Long toastCount
) {
}
