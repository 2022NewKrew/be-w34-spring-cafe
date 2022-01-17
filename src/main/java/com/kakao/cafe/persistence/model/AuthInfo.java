package com.kakao.cafe.persistence.model;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthInfo {

    @NotBlank
    private final String uid;
}
