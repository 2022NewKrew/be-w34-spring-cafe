package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {

    private int id;
    private String userId;
    private String name;
    private String email;

    public static UserProfileResponse from(User user) {
        return ModelMapperUtils.getModelMapper()
                .map(user, UserProfileResponse.class);
    }

}
