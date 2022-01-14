package com.kakao.cafe.domain;

import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class JdbcUserRepository implements UserRepository {

    @Override
    public User save(User user) {
        // TODO - 저장
        System.out.println("---------저장---------");
        return user;
    }

    @Override
    public List<User> findAll() {
        // TODO - 유저 목록
        System.out.println("---------유저 목록---------");
        return null;
    }

    @Override
    public User findByUserId(String id) {
        // TODO - 유저 아이디 검색
        System.out.println("---------유저 아이디 검색---------");
        return null;
    }
}
