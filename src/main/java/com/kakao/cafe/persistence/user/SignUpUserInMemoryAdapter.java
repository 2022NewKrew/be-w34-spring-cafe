package com.kakao.cafe.persistence.user;

import com.kakao.cafe.domain.user.SignUpUserPort;
import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Repository;

import static com.kakao.cafe.persistence.user.FindUserInMemoryAdapter.users;

@Repository
public class SignUpUserInMemoryAdapter implements SignUpUserPort {
    @Override
    public void save(User user) {
        users.put(user.getUserId(), user);
    }
}
