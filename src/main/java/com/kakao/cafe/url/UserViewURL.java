package com.kakao.cafe.url;

import com.kakao.cafe.model.Url;

public enum UserViewURL {
    USER_SIGN_IN("/list", true),
    USER_GET_LIST_VIEW("/list", false),
    USER_GET_PROFILE_VIEW("/profile", false),
    ;

    private static final String USER_MAPPING_HOST_URL = "/user";

    Url mappingUrl;

    UserViewURL(String mappingPath, boolean isRedirect) {
        this.mappingUrl = new Url(mappingPath, isRedirect);
    }

    public String getMappingUrl() {
        return mappingUrl.generateMappingUrl(USER_MAPPING_HOST_URL);
    }
}
