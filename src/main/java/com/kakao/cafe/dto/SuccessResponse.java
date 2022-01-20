package com.kakao.cafe.dto;

public class SuccessResponse {
    private int status;
    private Object data;

    public SuccessResponse(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
