package com.kakao.cafe.dto.post;

import com.kakao.cafe.util.consts.CafeConst;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class PageDto {
    private List<Integer> pageList;
    private int prevPage;
    private int nextPage;
    private final int curPage;
    private final int totalPageSize;

    public PageDto(int curPage, int totalPageSize) {
        this.curPage = curPage;
        this.totalPageSize = totalPageSize;

        setPage();
    }

    private void setPage() {
        prevPage = curPage == 1 ? 1 : curPage - 1;
        nextPage = curPage == totalPageSize ? totalPageSize : curPage + 1;

        int startNum = curPage / CafeConst.PAGE_GAP * CafeConst.PAGE_GAP + 1;
        if (curPage % CafeConst.PAGE_GAP == 0) {
            startNum -= CafeConst.PAGE_GAP;
        }

        pageList = IntStream.range(startNum, startNum + CafeConst.PAGE_GAP).boxed()
                .filter(page -> page <= totalPageSize)
                .collect(Collectors.toList());
    }
}
