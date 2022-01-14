package com.kakao.cafe.persistence.user.memory;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserDaoPort;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryUserDaoAdaptor implements UserDaoPort {
    private final Map<String, User> users;

    public InMemoryUserDaoAdaptor() {
        this.users = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public Map<String, User> getUsers() {
        return users;
    }
}
