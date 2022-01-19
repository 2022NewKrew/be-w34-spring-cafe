package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SessionUser extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    public SessionUser(Long userId, String nickname) {
        super(userId, null, null, nickname, null);
    }

    public static SessionUser from(User user) {
        return new SessionUser(user.getId(), user.getNickname());
    }
}
