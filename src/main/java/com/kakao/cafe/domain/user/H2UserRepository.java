package com.kakao.cafe.domain.user;

import java.util.List;

public class H2UserRepository implements UserRepository{
    @Override
    public void save(User user) {

    }

    @Override
    public void update(String id, String password, User user) {

    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
