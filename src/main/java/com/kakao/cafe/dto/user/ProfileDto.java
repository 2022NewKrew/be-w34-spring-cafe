package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileDto {
    private Long id;
    private String nickName;
    private String password;

    public void updateUser(User user) {
        user.setNickName(nickName);
        user.setPassword(password);
    }
}
