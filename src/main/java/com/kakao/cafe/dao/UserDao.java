package com.kakao.cafe.dao;

import com.kakao.cafe.vo.UserVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    private final List<UserVo> users = new ArrayList<>();

    public void addUser(UserVo userVo) {
        users.add(userVo);
    }

    public UserVo getUser(String userId) {
        return users.stream()
                .filter(userVo -> userVo.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public void updateUser(UserVo newUserVo) {
        UserVo curUserVo = getUser(newUserVo.getUserId());
        int index = users.indexOf(curUserVo);
        users.set(index, newUserVo);
    }

    public List<UserVo> getUsers() {
        return users;
    }
}
