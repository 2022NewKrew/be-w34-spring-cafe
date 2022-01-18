package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void createUser(User user);

    boolean isUserIdUsed(String userId);

    Collection<User> readUserList();

    User readByUserId(String userId);

    User updateUser(User user);
}
