package com.kakao.cafe.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PageInfo {

    public static class QnaPageInfo {
        public static final int PAGE_NUMBER_LIST_SIZE = 5;

        private Integer firstPageOfNextGroup;
        private Integer endPageOfPreviousGroup;
        private List<Integer> pageNumberList;
        private Boolean isEndGroupOfPage;
        private Boolean isFirstGroupOfPage;

        public QnaPageInfo(Page<QnaDto.QnaResponse> qnaList) {
            Pageable pageable = qnaList.getPageable();

            this.endPageOfPreviousGroup = calculateGroupNumber(pageable.getPageNumber()) * PAGE_NUMBER_LIST_SIZE;
            this.isEndGroupOfPage = isEndGroup(qnaList.getTotalPages(), pageable.getPageNumber());
            this.isFirstGroupOfPage = isFirstGroup(pageable.getPageNumber());
            this.firstPageOfNextGroup = (calculateGroupNumber(pageable.getPageNumber()) + 1) * PAGE_NUMBER_LIST_SIZE + 1;
            makePageNumberList(qnaList.getTotalPages(), pageable.getPageNumber());
        }

        private void makePageNumberList(int totalPages, int currentPageNumber) {
            int pageGroupIndex = currentPageNumber / PAGE_NUMBER_LIST_SIZE;

            List<Integer> pageNumberList = new ArrayList<>();

            for (int i = 0; i < PAGE_NUMBER_LIST_SIZE; i++) {
                int pageNumber = pageGroupIndex * PAGE_NUMBER_LIST_SIZE + i + 1;
                if (pageNumber > totalPages) {
                    break;
                }
                pageNumberList.add(pageNumber);
            }

            this.pageNumberList = pageNumberList;
        }

        private boolean isEndGroup(int totalPages, int currentPageNumber) {
            return calculateGroupNumber(totalPages) == calculateGroupNumber(currentPageNumber);
        }

        private boolean isFirstGroup(int currentPageNumber) {
            return calculateGroupNumber(currentPageNumber) == 0;
        }

        private int calculateGroupNumber(int pageNumber) {
            return pageNumber / PAGE_NUMBER_LIST_SIZE;
        }

        public Integer getEndPageOfPreviousGroup() {
            return endPageOfPreviousGroup;
        }

        public Integer getFirstPageOfNextGroup() {
            return firstPageOfNextGroup;
        }

        public List<Integer> getPageNumberList() {
            return pageNumberList;
        }

        public Boolean getEndGroupOfPage() {
            return isEndGroupOfPage;
        }

        public Boolean getFirstGroupOfPage() {
            return isFirstGroupOfPage;
        }
    }
}
