package com.kakao.cafe.url;

import com.kakao.cafe.model.Url;

public enum PostViewURL {
    POST_WRITE("", true),
    POST_GET_LIST_VIEW("", false),
    POST_GET_CONTENT_VIEW("show", false),
    ;

    private static final String POST_MAPPING_HOST_URL = "/user";

    Url mappingUrl;

    PostViewURL(String mappingPath, boolean isRedirect) {
        this.mappingUrl = new Url(mappingPath, isRedirect);
    }

    public String getMappingUrl() {
        return this.mappingUrl.generateMappingUrl(POST_MAPPING_HOST_URL);
    }
}
