package com.kakao.cafe.persistence.user;

import com.kakao.cafe.domain.user.UpdateUserPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserDaoPort;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateUserInMemoryAdaptor implements UpdateUserPort {

    private final UserDaoPort userDaoPort;

    public UpdateUserInMemoryAdaptor(UserDaoPort userDaoPort) {
        this.userDaoPort = userDaoPort;
    }

    @Override
    public void save(User user) {
        userDaoPort.getUsers().put(user.getUserId(), user);
    }
}
