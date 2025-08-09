package com.app.toaster.mainpage.model;

import com.app.toaster.mainpage.vo.CategoryToastCountVO;
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
