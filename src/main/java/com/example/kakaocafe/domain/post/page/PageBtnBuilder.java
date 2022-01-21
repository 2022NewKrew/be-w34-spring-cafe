package com.example.kakaocafe.domain.post.page;

import com.example.kakaocafe.core.meta.PageConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class PageBtnBuilder {
    public static final PageBtnBuilder builder = new PageBtnBuilder();

    private int currentPage;
    private BiFunction<Integer, Integer, Integer> getNumOfPostsFunction;

    public PageBtnBuilder currentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public PageBtnBuilder getNumOfPostsFunction(BiFunction<Integer, Integer, Integer> getNumOfPostsFunction) {
        this.getNumOfPostsFunction = getNumOfPostsFunction;
        return this;
    }

    public List<PageBtn> build() {
        // 전체 단위는 오프셋 당 페이지가 존재하고, 페이지당 게시글이 존재함.
        final int startingPageOfCurOffset = calcStartingPage(currentPage);

        final int numOfPosts = getNumOfPosts(startingPageOfCurOffset);
        final int numOfPages = calcNumOfPages(numOfPosts);

        final int endingPageOfCurOffset = calcEndingPage(startingPageOfCurOffset, numOfPages);

        final int startingPageOfPrevOffset = calcPreOffset(startingPageOfCurOffset);
        final int startingPageOfNextOffset = calcNextOffset(numOfPosts, endingPageOfCurOffset);

        final List<PageBtn> pageBtnList = new ArrayList<>();
        pageBtnList.add(PageBtn.generatePrevBtn(startingPageOfPrevOffset + 1));
        IntStream.rangeClosed(startingPageOfCurOffset, endingPageOfCurOffset)
                .forEach(i -> pageBtnList.add(PageBtn.generateOrderBtn(i + 1, currentPage)));
        pageBtnList.add(PageBtn.generateNextBtn(startingPageOfNextOffset + 1));
        return pageBtnList;
    }

    private int getNumOfPosts(int startingPageOfCurOffset) {
        final int offset = startingPageOfCurOffset * PageConfig.NUM_OF_POSTS_PER_PAGE;
        final int limit = PageConfig.NUM_OF_PAGES_PER_OFFSET * PageConfig.NUM_OF_POSTS_PER_PAGE + 1;

        return getNumOfPostsFunction.apply(offset, limit);
    }

    private int calcNumOfPages(int numOfPages) {
        // 게시글 수로 페이지를 계산
        return (calcNumOfPostsLessThenMax(numOfPages) / PageConfig.NUM_OF_POSTS_PER_PAGE) + (calcNumOfPostsLessThenMax(numOfPages) % PageConfig.NUM_OF_POSTS_PER_PAGE > 0 ? 1 : 0);
    }

    private int calcNumOfPostsLessThenMax(int numOfPosts) {
        // 최대 로우수보다 작은 게시글 수
        return Math.min(numOfPosts, maxOfPosts());
    }

    private int maxOfPosts() {
        // 최대 로우수는 오프셋당 페이지수 * 페이지당 게시글 수
        return PageConfig.NUM_OF_PAGES_PER_OFFSET * PageConfig.NUM_OF_POSTS_PER_PAGE;
    }

    private int calcStartingPage(int page) {
        // 시작 페이지를 계산
        // 현재 페이지에서 오프셋당 페이지 수를 나누고
        // 오프셋당 페이지수를 곱한다
        // 페이지가 보여질떄는 1부터 보여지기 때문
        // 7페이지라면 6/5*5 = 5 => 시작페이지는 5
        return Math.max(page - 1, 0) / PageConfig.NUM_OF_PAGES_PER_OFFSET * PageConfig.NUM_OF_PAGES_PER_OFFSET;
    }

    private int calcEndingPage(int startingPage, int numOfPages) {
        return startingPage + numOfPages - 1;
    }

    public int calcPreOffset(int offset) {
        // 이전 오프셋을 계산
        // 현재 오프셋에서 오프셋당 페이지수를 뺀다.
        return Math.max(offset - PageConfig.NUM_OF_PAGES_PER_OFFSET, 0);
    }

    public int calcNextOffset(int numOfPosts, int endingPageOfCurOffset) {
        // 최대 게시물을 가지고
        final boolean hasMaxOfPosts = (numOfPosts / PageConfig.NUM_OF_POSTS_PER_PAGE) == PageConfig.NUM_OF_PAGES_PER_OFFSET;
        // 다음 오프셋에서도 게시글이 존재하면
        final boolean hasPostsOfNextOffset = numOfPosts % PageConfig.NUM_OF_POSTS_PER_PAGE > 0;
        // 조건에 맞는 캐리값
        final int carry = (hasMaxOfPosts && hasPostsOfNextOffset) ? 1 : 0;

        return endingPageOfCurOffset + carry;
    }
}
