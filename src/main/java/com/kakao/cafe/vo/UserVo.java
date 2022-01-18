package com.kakao.cafe.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class UserVo {
    private long id;
    private String userId;

    @Override
    public boolean equals(Object obj) {
        UserVo otherUser = (UserVo) obj;
        if (this.id == otherUser.getId() && this.userId == otherUser.getUserId()) {
            return true;
        }
        return false;
    }

}
