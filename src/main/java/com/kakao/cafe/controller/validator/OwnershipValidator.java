package com.kakao.cafe.controller.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class OwnershipValidator {

    public void validate(String loginUserId, String ownerUserId) {
        if (!loginUserId.equals(ownerUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다", new IllegalArgumentException());
        }
    }
}
