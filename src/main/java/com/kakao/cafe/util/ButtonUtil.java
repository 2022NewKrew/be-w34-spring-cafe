package com.kakao.cafe.util;

import com.kakao.cafe.vo.Article;

import java.util.List;

public class ButtonUtil {

    private ButtonUtil() {}

    public static boolean hasLeftButton(int pageIndex) {
        if(pageIndex == 0)
            return false;
        return true;
    }

    public static boolean hasRightButton(List<Article> articles, int pageIndex) {
        int maxPageNumber = (articles.size() - 1) / 15 + 1;
        int maxPageIndex = (maxPageNumber - 1) / 5;
        if(pageIndex == maxPageIndex)
            return false;
        return true;
    }
}
