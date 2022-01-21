package com.kakao.cafe.domain;

import com.kakao.cafe.util.Checker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pages {
    private final List<Page> list = new ArrayList<>();

    public Pages(final int start, final int end, final int current) {
        Checker.checkIntMinMax(start, end);
        Checker.checkIntMinMax(start, current);
        Checker.checkIntMinMax(current, end);
        for (int i = start; i <= end; i++) {
            list.add(new Page(i, i == current));
        }
    }

    public List<Page> getList() {
        return Collections.unmodifiableList(list);
    }
}
