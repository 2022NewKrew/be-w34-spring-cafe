package com.kakao.cafe.repository;

import org.springframework.stereotype.Repository;
import com.kakao.cafe.dto.UserDto;
import java.util.List;
import java.util.ArrayList;

/**
 *  DB 연결과 관련된 정보를 갖는 DAO 입니다.
 *  아직 DB를 사용하지 않아서 임시로 이렇게만 구현해놨습니다.
 */
@Repository
public class UserRepository {
    private final List<UserDto> userList = new ArrayList<>();

    public void save(UserDto user) {
        userList.add(user);
    }

    public UserDto findById(String userId) {
        for (UserDto user : userList) {
            if (user.getUserId().equals(userId))
                return user;
        }
        return null;
    }

    public List<UserDto> getAllUsers() {
        return userList;
    }
}
