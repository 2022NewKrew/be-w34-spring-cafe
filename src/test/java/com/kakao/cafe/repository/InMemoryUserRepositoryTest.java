package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {
    InMemoryUserRepository repository = new InMemoryUserRepository();

    @AfterEach
    public void afterEach(){
        repository.clearAll();
    }

    @Test
    public void save(){
        User user = new User();
        user.setNickname("weather");

        repository.save(user);

        User result = repository.findById(user.getId()).get();
        assertEquals(result, user);
    }

    @Test
    public void findByUserId(){
        User user1 = new User();
        user1.setNickname("weather");
        repository.save(user1);

        User user2 = new User();
        user2.setNickname("white");
        repository.save(user2);

        User result = repository.findByNickname("weather").get();

        assertEquals(result, user1);
    }

    @Test
    public void findAll(){
        User user1 = new User();
        user1.setNickname("weather");
        repository.save(user1);

        User user2 = new User();
        user2.setNickname("white");
        repository.save(user2);

        List<User> result = repository.findAll();
        assertEquals(result.size(), 2);
    }


}