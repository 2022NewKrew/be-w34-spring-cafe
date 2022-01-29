package com.kakao.cafe.system;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by melodist
 * Date: 2022-01-29 029
 * Time: 오후 11:41
 */
@Data
public class LoginDto {

    @NotNull
    private String userId;

    @NotNull
    private String password;
}
