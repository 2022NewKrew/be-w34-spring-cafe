package com.kakao.cafe.users;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryUserRepositoryTest {

    UserRepository userRepository = new MemoryUserRepository();

    List<User> users = List.of(
            new User("1234", "1234", "1234", "1234"),
            new User("asdf", "asdf", "asdf", "asdf")
    );

    @Test
    void save() {
        User u = users.get(0);

        User actual = userRepository.save(u);

        Assertions.assertThat(u).isEqualTo(actual);
    }

    @Test
    void findByUserId() {
        User u = users.get(1);
        userRepository.save(u);

        User actual = userRepository.findByUserId(u.getUserId()).get();

        Assertions.assertThat(u).isEqualTo(actual);
    }

    @Test
    void findAll() {
        userRepository.save(users.get(0));
        userRepository.save(users.get(1));

        List<User> actual = userRepository.findAll();

        Assertions.assertThat(actual).hasSize(2);
    }
}