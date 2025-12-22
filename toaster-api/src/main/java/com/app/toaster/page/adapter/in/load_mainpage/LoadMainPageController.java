package com.app.toaster.page.adapter.in.load_mainpage;

import com.app.toaster.page.adapter.in.load_mainpage.LoadMainPageCommand;
import com.app.toaster.page.adapter.in.load_mainpage.LoadMainPageUseCase;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/users")
@RequiredArgsConstructor
class LoadMainPageController {

    private final LoadMainPageUseCase loadMainPageUseCase;

    @GetMapping("/main")
    @Deprecated
    protected ApiResponse<?> LoadUserMainPage(Long userId){ //jwt추가하고 어노테이션으로 변경
        return ApiResponse.success(Success.GET_SETTINGS_SUCCESS, loadMainPageUseCase.loadMainPage(LoadMainPageCommand.toCommand(userId)));
    }
}
