package com.kakao.cafe.util;

public class PagingRequest {
    private int limit;
    private int offset;

    public PagingRequest(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
