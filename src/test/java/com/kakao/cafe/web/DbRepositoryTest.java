package com.kakao.cafe.web;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class DbRepositoryTest {

    Logger logger = LoggerFactory.getLogger(DbRepository.class);

    @Autowired
    DbRepository dbRepository;

    @Test
    void nullCheck() {
        assertThat(dbRepository).isNotNull();
    }

    @Test
    @Transactional
    void save() {
        User user = new User("test@test.com", "teddy");
        User savedUser = dbRepository.saveUser(user);
        logger.info("saved user: {}", savedUser);
        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    void findUser() {
        final String email = "honux@abc.com";
        User user = new User();
        user.setEmail(email);
        user = dbRepository.findUserByEmail(email);
        logger.info("Find User: {}",user);
        assertThat(user.getId()).isGreaterThan(0);
    }
}
