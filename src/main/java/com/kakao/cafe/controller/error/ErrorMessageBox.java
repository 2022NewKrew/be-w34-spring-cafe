package com.kakao.cafe.controller.error;

import lombok.Getter;

@Getter
public class ErrorMessageBox {
    private String message;

    public ErrorMessageBox(String message) {
        this.message = message;
    }
}
