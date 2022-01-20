package com.kakao.cafe.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {
    private PageUtils() {

    }

    public static List<Integer> makePageList(int size) {
        List<Integer> pageList = new ArrayList<>();
        for (int i = 1; i <= ((size - 1) / Constant.ARTICLE_PER_PAGE) + 1; i++) {
            pageList.add(i);
        }
        return pageList;
    }
}
