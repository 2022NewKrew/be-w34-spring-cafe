package com.kakao.cafe.model;

public class Url {
    private static final String REDIRECT_PREFIX = "redirect:";

    final private String mappingPath;
    final private boolean isRedirect;

    public Url(String mappingPath, boolean isRedirect) {
        this.mappingPath = mappingPath;
        this.isRedirect = isRedirect;
    }

    public String generateMappingUrl(final String defaultUrl) {
        if( defaultUrl == null ) {
            return "";
        }
        if(isRedirect) {
            return REDIRECT_PREFIX + defaultUrl + mappingPath;
        }
        return defaultUrl + mappingPath;
    }
}
