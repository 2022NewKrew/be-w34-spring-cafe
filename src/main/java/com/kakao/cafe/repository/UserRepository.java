package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void createUser(User targetUser);

    boolean isUserIdUsed(String targetUserId);

    Collection<User> readUserList();

    User readByUserId(String userId);
}
