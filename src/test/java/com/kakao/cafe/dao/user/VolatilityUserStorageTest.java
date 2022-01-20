package com.kakao.cafe.dao.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserFactory;
import com.kakao.cafe.model.user.UserId;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("VolatilityUserStorage 테스트")
class VolatilityUserStorageTest {

    private static final int PRECONDITION_USER_NUMBER = 10;

    private UserDao userDao = new VolatilityUserStorage();

    @BeforeEach
    private void before() {
        userDao = new VolatilityUserStorage();
        for (int i = 0; i < PRECONDITION_USER_NUMBER; i++) {
            userDao.addUser(
                    UserFactory.getUser("userId" + i, "password" + i, "name" + i, "email" + i)
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
        User newUser = UserFactory.getUser("userId", "password", "name", "email");
        //when
        userDao.addUser(newUser);
        User user = userDao.findUserById(newUser.getUserId()).orElseGet(null);

        //then
        assertThat(user.getUserId().getValue()).isEqualTo("userId");
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
        User user = UserFactory.getUser("userId" + 0, "password" + 0, "newName", "newEmail");
        //when
        userDao.update(user);
        user = userDao.findUserById(new UserId("userId" + 0)).orElseGet(null);

        //then
        assertThat(user.getName().getValue()).isEqualTo("newName");
        assertThat(user.getEmail().getValue()).isEqualTo("newEmail");
    }
}
