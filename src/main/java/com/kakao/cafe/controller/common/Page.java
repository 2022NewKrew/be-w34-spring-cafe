package com.kakao.cafe.controller.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Page {
    String className;
    String buttonName;
    int pageNumber;

    /**
     * 무스타치라서 다음과 같이 Model에 넣을 에트리뷰트를 생성합니다.
     *
     * @param currentPage    현재 보이는 페이지
     * @param endPage        가장 마지막 페이지
     * @param pageButtonSize 이전 다음으로 로딩할 페이지 수
     * @return Model에 들어가 페이지 버튼에 대항 정보
     */
    public static List<Page> getPageList(int currentPage, int endPage, int pageButtonSize) {
        List<Page> result = new ArrayList<>();

        int startNumber = ((currentPage - 1) / pageButtonSize) * pageButtonSize + 1;
        int endNumber = Math.min(startNumber + pageButtonSize - 1, endPage);

        if (startNumber > pageButtonSize) {
            result.add(new Page("prev", "이전", startNumber - 1));
        }

        for (int index = startNumber; index <= endNumber; index++) {
            result.add(new Page(index == currentPage ? "kakao-current-page" : "", String.valueOf(index), index));
        }

        if (endNumber < endPage) {
            result.add(new Page("next", "다음", endNumber + 1));
        }

        return result;

    }
}
