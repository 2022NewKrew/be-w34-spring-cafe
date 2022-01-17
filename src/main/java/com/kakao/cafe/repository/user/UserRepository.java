package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    void save(User user);
    void update(UserId id, Password password, User user);
    User findById(UserId id);
    List<User> findAll();
}
