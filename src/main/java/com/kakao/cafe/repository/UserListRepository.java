package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class UserListRepository implements UserRepository {
    private List<User> userList;

    public UserListRepository() {
        userList = new ArrayList<>();

        userList.add(User.builder()
                .email("kina.lee@kakaocorp.com")
                .username("키나")
                .password("1234")
                .build());
        userList.add(User.builder()
                .email("eroica.b@kakaocorp.com")
                .username("에로이카")
                .password("1234")
                .build());
        userList.add(User.builder()
                .email("june.kim@kakaocorp.com")
                .username("준")
                .password("1234")
                .build());
        IntStream.rangeClosed(1, 50).forEach(value -> {
            userList.add(User.builder()
                    .email(String.format("tester.%d@kakaocorp.com", value))
                    .username(String.format("테스터%d", value))
                    .password("1234")
                    .build());
        });
    }

    @Override
    public void save(User entity) {
        userList.stream()
                .filter(user -> user.getEmail().equals(entity.getEmail()))
                .findFirst()
                .ifPresentOrElse(
                        user -> {
                            user.changeUsername(entity.getUsername());
                            user.changePassword(entity.getPassword());
                        },
                        () -> userList.add(entity)
                );
    }

    @Override
    public void update(User entity) {
        Optional<User> result = userList.stream()
                .filter(user -> user.getEmail().equals(entity.getEmail()))
                .filter(user -> user.getPassword().equals(entity.getPassword()))
                .findFirst();
        result.ifPresent(user -> {
        });
    }

    @Override
    public Optional<User> findByEmail(User entity) {
        return userList.stream()
                .filter(user -> user.getEmail().equals(entity.getEmail()))
                .findFirst();
    }
    
    @Override
    public Page<User> findAll(Pageable pageable) {
        int totalPage = (int) Math.ceil(userList.size() / (double) pageable.getSize());
        int fromIndex = pageable.getPage() * pageable.getSize();
        if (fromIndex >= userList.size())
            return new Page<User>(new ArrayList<>(), totalPage, userList.size(), pageable);
        int toIndex = fromIndex + pageable.getSize();
        if (toIndex >= userList.size())
            toIndex = userList.size();
        return new Page<User>(userList.subList(fromIndex, toIndex), totalPage, userList.size(), pageable);
    }
}
