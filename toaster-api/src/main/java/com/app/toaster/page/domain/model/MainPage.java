package com.app.toaster.page.domain.model;

import com.app.toaster.page.domain.vo.CategoryToastCountVO;
import lombok.Getter;

import java.util.List;

@Getter
public class MainPage {

    String nickname;
    int readToastNum;
    int allToastNum;
    List<CategoryToastCountVO> toastcountList;

    public MainPage(String nickname, int readToastNum, int allToastNum, List<CategoryToastCountVO> toastcountList) {
        this.nickname = nickname;
        this.readToastNum = readToastNum;
        this.allToastNum = allToastNum;
        this.toastcountList = toastcountList;
    }

    public static MainPage makeMainPage(String nickname, int readToastNum, int allToastNum, List<CategoryToastCountVO> toastcountList) {
        return new MainPage(nickname, readToastNum, allToastNum, toastcountList);

    }
}
