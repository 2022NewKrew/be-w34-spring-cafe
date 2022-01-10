package com.kakao.cafe.url;

public enum UsersViewURL {
    USER_SIGN_IN("", true),
    USER_GET_LIST_VIEW("/list.html", false),
    USER_GET_PROFILE_VIEW("/profile.html", false),
    ;

    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String USER_MAPPING_DEFAULT_URL = "/user";

    private String mappingUrl;
    private boolean isRedirect;
    UsersViewURL(String mappingUrl) {
        this.mappingUrl = mappingUrl;
    }
    UsersViewURL(String mappingUrl, boolean isRedirect) {
        this(mappingUrl);
        this.isRedirect = isRedirect;
    }

    public String getMappingUrl() {
        StringBuilder sb = new StringBuilder();
        if(this.isRedirect) {
            sb.append(REDIRECT_PREFIX);
        }
        sb.append(this.USER_MAPPING_DEFAULT_URL);
        sb.append(this.mappingUrl);
        return sb.toString();
    }
}
