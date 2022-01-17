package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.user.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("VolatilityUserStorage 테스트")
class VolatilityUserStorageTest {
    private static final int PRECONDITION_USER_NUMBER = 10;

    private UserDao userDao = new VolatilityUserStorage();

    @BeforeEach
    private void before() {
        userDao = new VolatilityUserStorage();
        for (int i = 0; i < PRECONDITION_USER_NUMBER; i++) {
            userDao.addUser(
                    new UserId("userId" + i),
                    new Password("password" + i),
                    new Name("name" + i),
                    new Email("email" + i)
            );
        }
    }

    @AfterEach
    private void after() {
        userDao = null;
    }

    @DisplayName("설정된 초기 값이 존재할 때 getUsers 메서드를 실행하면 저장한 모든 User를 가져온다.")
    @Test
    public void getUsers() {
        //give
        int index = 0;
        UserId expectedUserId = new UserId("userId" + index);

        //when
        List<User> users = userDao.getUsers();

        //then
        assertThat(users.size()).isEqualTo(PRECONDITION_USER_NUMBER);
        assertThat(users.get(index).getUserId()).isEqualTo(expectedUserId);
    }

    @DisplayName("설정된 초기 값이 존재할 때 addUser 메서드를 실행하면 새로운 User를 추가한다.")
    @Test
    public void addUser() {
        //give
        UserId userId = new UserId("userId");
        Password password = new Password("password");
        Name name = new Name("name");
        Email email = new Email("email");

        //when
        userDao.addUser(userId, password, name, email);
        User user = userDao.findUserById(userId).orElseGet(null);

        //then
        assertThat(user.getUserId()).isEqualTo(userId);
    }

    @DisplayName("설정된 초기 값이 존재할 때 findUserById 메서드를 실행하면 기다하는 값을 가져온다.")
    @Test
    public void findUserById() {
        //give
        UserId userId = new UserId("userId" + 0);

        //when
        User user = userDao.findUserById(userId).orElseGet(null);

        //then
        assertThat(user.getUserId()).isEqualTo(userId);
    }

    @DisplayName("설정된 초기 값이 존재할 때 getSize 메서드를 실행하면 User의 개수를 가져온다.")
    @Test
    public void getSize() {
        //give
        //when
        int size = userDao.getSize();

        //then
        assertThat(size).isEqualTo(PRECONDITION_USER_NUMBER);
    }

    @DisplayName("설정된 초기값이 존재할때 입력받은 아이디를 가지고 있는 유저의 이름, 이메일 정보를 수정한다.")
    @Test
    public void update() {
        //give
        UserId userId = new UserId("userId" + 0);
        Name name = new Name("newName");
        Email email = new Email("newEmail");
        //when
        userDao.update(userId, name, email);
        User user = userDao.findUserById(userId).orElseGet(null);

        //then
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
    }
}
