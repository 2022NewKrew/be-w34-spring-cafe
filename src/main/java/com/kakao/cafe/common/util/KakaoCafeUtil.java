package com.kakao.cafe.common.util;

import com.kakao.cafe.user.dto.response.UserInfoResponse;

import javax.servlet.http.HttpSession;

public class KakaoCafeUtil {

    public static UserInfoResponse getUserInfoInSession(HttpSession session) {
        return (UserInfoResponse) session.getAttribute("user");
    }
}
