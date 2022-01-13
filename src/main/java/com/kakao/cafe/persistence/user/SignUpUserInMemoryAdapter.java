package com.kakao.cafe.persistence.user;

import com.kakao.cafe.domain.user.SignUpUserPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserDaoPort;
import org.springframework.stereotype.Repository;

@Repository
public class SignUpUserInMemoryAdapter implements SignUpUserPort {

    private final UserDaoPort userDaoPort;

    public SignUpUserInMemoryAdapter(UserDaoInMemoryAdaptor userDaoPort) {
        this.userDaoPort = userDaoPort;
    }

    @Override
    public void save(User user) {
        userDaoPort.getUsers().put(user.getUserId(), user);
    }
}
