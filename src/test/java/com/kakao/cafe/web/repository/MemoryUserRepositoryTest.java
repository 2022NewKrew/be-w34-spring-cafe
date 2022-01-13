package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.repository.user.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryUserRepositoryTest {

    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        User user = new User();
        user.setUserId("test");

        repository.save(user);

        User result = repository.findById(user.getId()).get();
        assertThat(user).isEqualTo(result);
    }

    @Test
    void findById() {
        User user1 = new User();
        user1.setUserId("user1");
        repository.save(user1);

        User user2 = new User();
        user2.setUserId("user2");
        repository.save(user2);

        User result = repository.findById(user1.getId()).get();

        assertThat(user1).isEqualTo(result);
    }

    @Test
    void findByUserId() {
        User user1 = new User();
        user1.setUserId("user1");
        repository.save(user1);

        User user2 = new User();
        user2.setUserId("user2");
        repository.save(user2);

        User result = repository.findByUserId("user1").get();

        assertThat(user1).isEqualTo(result);

    }

    @Test
    void findAll() {
        User user1 = new User();
        user1.setUserId("user1");
        repository.save(user1);

        User user2 = new User();
        user2.setUserId("user2");
        repository.save(user2);

        List<User> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
