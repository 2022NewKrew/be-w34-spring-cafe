package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class UserRepository {
    private List<User> userList;

    public UserRepository() {
        userList = new ArrayList<>();

        userList.add(User.builder()
                .email("kina.lee@kakaocorp.com")
                .username("키나")
                .password("password1234")
                .build());
        userList.add(User.builder()
                .email("eroica.b@kakaocorp.com")
                .username("에로이카")
                .password("password1234")
                .build());
        userList.add(User.builder()
                .email("june.kim@kakaocorp.com")
                .username("준")
                .password("password1234")
                .build());
        IntStream.rangeClosed(1, 50).forEach(value -> {
            userList.add(User.builder()
                    .email(String.format("tester.%d@kakaocorp.com", value))
                    .username(String.format("테스터%d", value))
                    .password("password1234")
                    .build());
        });
    }

    public void save(User entity) {
        userList.add(entity);
    }
}
