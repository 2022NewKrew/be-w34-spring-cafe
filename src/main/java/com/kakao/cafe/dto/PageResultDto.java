package com.kakao.cafe.dto;

import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDto<DTO, EN> {
    private List<DTO> dtoList;

    private int totalPage;

    private int page;
    private int size;
    private int start, end;

    private int prev, next;
    private boolean hasPrev, hasNext;

    private List<Integer> pageList;

    public PageResultDto(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.getEntityList().stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPage();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPage() + 1;
        this.size = pageable.getSize();

        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
        start = tempEnd - 9;
        hasPrev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        hasNext = totalPage > tempEnd;
        if (hasPrev)
            prev = start - 1;
        if (hasNext)
            next = end + 1;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
