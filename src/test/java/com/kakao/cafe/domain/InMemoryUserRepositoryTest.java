package com.kakao.cafe.domain;

import com.kakao.cafe.exceptions.DuplicateUserException;
import com.kakao.cafe.exceptions.UserNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InMemoryUserRepositoryTest {

    private final String id = "id";
    private final String userId = "userId";
    private final String userId2 = "userId2";
    private final String password = "password";
    private final String name = "name";
    private final String email = "email";

    @Test
    @DisplayName("[성공] InMemoryUserRepository 클래스 생성")
    void InMemoryUserRepository() {
        new InMemoryUserRepository();
    }

    @Test
    @DisplayName("[성공] User 저장")
    void save() {
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        User user = new User(id, userId, password, name, email);

        inMemoryUserRepository.save(user);
    }

    @Test
    @DisplayName("[실패] 중복된 User Id 저장")
    void save_FailedBy_DuplicatedUser() {
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        User user = new User(id, userId, password, name, email);

        inMemoryUserRepository.save(user);

        Assertions.assertThrows(DuplicateUserException.class,
                () -> inMemoryUserRepository.save(user));
    }

    @Test
    @DisplayName("[성공] User 리스트")
    void findAll() {
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        User user1 = new User(id, userId, password, name, email);
        User user2 = new User(id, userId2, password, name, email);
        inMemoryUserRepository.save(user1);
        inMemoryUserRepository.save(user2);
        List<User> userList_Answer = List.of(user1, user2);

        List<User> userList = inMemoryUserRepository.findAll();

        Assertions.assertEquals(userList, userList_Answer);
    }

    @Test
    @DisplayName("[성공] User ID로 검색")
    void findByUserId() {
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        User user1 = new User(id, userId, password, name, email);
        User user2 = new User(id, userId2, password, name, email);
        inMemoryUserRepository.save(user1);
        inMemoryUserRepository.save(user2);

        User findUser = inMemoryUserRepository.findByUserId(userId);

        Assertions.assertEquals(findUser, user1);
    }

    @Test
    @DisplayName("[실패] 존재하지 않는 User ID로 검색")
    void findByUserId_FailedBy_InvalidUserId() {
        String invalidUserId = "InvalidUserId";
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        User user1 = new User(id, userId, password, name, email);
        inMemoryUserRepository.save(user1);

        Assertions.assertThrows(UserNotFoundException.class,
                () -> inMemoryUserRepository.findByUserId(invalidUserId));
    }
}
