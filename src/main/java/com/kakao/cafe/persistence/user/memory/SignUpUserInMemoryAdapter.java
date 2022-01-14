package com.kakao.cafe.persistence.user.memory;

import com.kakao.cafe.domain.user.SignUpUserPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserDaoPort;

//@Repository
public class SignUpUserInMemoryAdapter implements SignUpUserPort {

    private final UserDaoPort userDaoPort;

    public SignUpUserInMemoryAdapter(InMemoryUserDaoAdaptor userDaoPort) {
        this.userDaoPort = userDaoPort;
    }

    @Override
    public void save(User user) {
        userDaoPort.getUsers().put(user.getUserId(), user);
    }
}
