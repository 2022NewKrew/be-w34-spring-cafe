package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserSignUpRequest;
import com.kakao.cafe.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public static User fromNoDbIndex(UserSignUpRequest userSignUpRequest) {
        return ModelMapperUtils.getModelMapper()
                .map(userSignUpRequest, User.class);
    }

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
