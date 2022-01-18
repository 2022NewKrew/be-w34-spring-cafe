package com.kakao.cafe.controller.listview;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public abstract class ListViewController<T> {

    public ModelAndView listView(Integer pageNum, ModelAndPageView mapv) {
        int numOfElement = getNumOfElement();
        mapv.addObject("numOfElement", numOfElement);

        Integer pageSize = getPageSize();
        Integer totalPageNum = numOfElement / pageSize + 1;
        mapv.setPageNumbers(pageNum, totalPageNum);

        List<T> simpleElementInfos = getSimpleElementInfos(pageNum, pageSize);
        mapv.addObject("elementInfos", simpleElementInfos);

        mapv.setViewName(getListViewName());

        return mapv;
    }

    protected abstract int getNumOfElement();

    protected abstract int getPageSize();

    protected abstract List<T> getSimpleElementInfos(Integer pageNum, Integer pageSize);

    protected abstract String getListViewName();
}
