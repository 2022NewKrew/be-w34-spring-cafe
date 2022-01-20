package com.kakao.cafe.app.request;

import com.kakao.cafe.service.dto.ModifyUserDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class ModifyUserRequest {

    @Pattern(regexp="|.{6,20}")
    private final String password;

    @Length(min=2, max=20)
    private final String name;

    @Email
    private final String email;

    public ModifyUserRequest(
            String password,
            String name,
            String email
    ) {
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public ModifyUserDto toModifyUserDto() {
        return new ModifyUserDto(password, name, email);
    }
}
