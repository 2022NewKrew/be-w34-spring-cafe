package com.kakao.cafe.web;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DbRepositoryTest {

    @Autowired
     DbRepository dbRepository;

    @Test
    void nullCheck() {
        assertThat(dbRepository).isNotNull();
    }

    @Test
    void save() {
        User user = new User("honux@abc.com", "honux");
        User savedUser = dbRepository.saveUser(user);
        assertThat(user).isEqualTo(user);
    }
}
