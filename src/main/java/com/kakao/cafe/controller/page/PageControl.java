package com.kakao.cafe.controller.page;

import com.kakao.cafe.util.Checker;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class PageControl {
    private static final String LAST_PAGE_COOKIE_KEY = "lastPage";
    private static final Cookie DEFAULT_LAST_PAGE = new Cookie(LAST_PAGE_COOKIE_KEY, "1");

    private PageControl() {}

    public static Cookie createLastPageCookie(final int page) {
        Checker.checkPage(page);
        Cookie cookie = new Cookie(LAST_PAGE_COOKIE_KEY, String.valueOf(page));
        cookie.setMaxAge(Integer.MIN_VALUE);
        cookie.setPath("/");

        return cookie;
    }

    public static int getLastPage(final HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();

        return Integer.parseInt(
                Arrays.stream(cookies)
                        .filter(c -> c.getName().equals(LAST_PAGE_COOKIE_KEY))
                        .findFirst()
                        .orElse(DEFAULT_LAST_PAGE)
                        .getValue(),
                10);
    }
}
