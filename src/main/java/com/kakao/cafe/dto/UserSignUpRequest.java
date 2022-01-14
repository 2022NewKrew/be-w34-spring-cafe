package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.utils.ModelMapperUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequest {

    private String userId;
    private String password;
    private String name;
    private String email;

    public static User getUserFromNoDbIndex(UserSignUpRequest userSignUpRequest) {
        return ModelMapperUtils.getStrictModelMapper()
                .map(userSignUpRequest, User.class);
    }

}
