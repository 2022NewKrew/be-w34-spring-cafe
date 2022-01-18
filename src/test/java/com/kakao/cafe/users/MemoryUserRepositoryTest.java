package com.kakao.cafe.users;

import com.kakao.cafe.CafeApplicationTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryUserRepositoryTest extends CafeApplicationTests {

    UserRepository userRepository = new MemoryUserRepository();

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