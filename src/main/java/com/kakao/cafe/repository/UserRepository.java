package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void createUser(UserDto userDto);
    boolean isUserIdUsed(String userId);
    List<User> findAllUsers();
    User findUserByUserId(String userId);
    User updateUser(User user);
    String findUidByUserId(String userId);
}
