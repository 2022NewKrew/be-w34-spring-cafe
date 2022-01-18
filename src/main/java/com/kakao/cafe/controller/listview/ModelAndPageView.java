package com.kakao.cafe.controller.listview;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndPageView extends ModelAndView {
    public static final int INDEX_NUM_PER_PAGE = 5;

    public static final String CURRENT_PAGE = "currentPage";
    public static final String START_INDEX = "startIndex";
    public static final String LAST_INDEX = "lastIndex";

    public void setPageNumbers(Integer currentPage, Integer totalPageNum) {
        this.addObject(CURRENT_PAGE, currentPage);
        this.addObject(START_INDEX, (currentPage / INDEX_NUM_PER_PAGE) * INDEX_NUM_PER_PAGE + 1);
        this.addObject(LAST_INDEX, (currentPage / INDEX_NUM_PER_PAGE) * INDEX_NUM_PER_PAGE + INDEX_NUM_PER_PAGE > totalPageNum ? totalPageNum : (currentPage / INDEX_NUM_PER_PAGE) * INDEX_NUM_PER_PAGE + INDEX_NUM_PER_PAGE);
    }
}
