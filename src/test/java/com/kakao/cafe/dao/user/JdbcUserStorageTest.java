package com.kakao.cafe.dao.user;

import com.kakao.cafe.dao.TestConfig;
import com.kakao.cafe.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JdbcUserStorage 테스트")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
class JdbcUserStorageTest {
    public static final int PRECONDITION_USER_LENGTH = 10;
    static UserDao userDao;

    @Autowired
    JdbcUserStorageTest(DataSource dataSource) {
        userDao = new JdbcUserStorage(new JdbcTemplate(dataSource));
    }

    @DisplayName("설정된 초기 값이 존재할 때 getUsers 메서드를 실행하면 저장한 모든 User를 가져온다.")
    @Test
    void getUsers() {
        //give
        //when
        List<User> users = userDao.getUsers();
        //then
        assertThat(users.size()).isEqualTo(PRECONDITION_USER_LENGTH);
        assertThat(users.get(0).getUserId()).isEqualTo("userId1");
    }

    @DisplayName("설정된 초기 값이 존재할 때 addUser 메서드를 실행하면 새로운 User를 추가한다.")
    @Test
    void addUser() {
        //give
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";

        //when
        userDao.addUser(userId, password, name, email);
        User user = userDao.findUserById(userId).orElseGet(null);

        //then
        assertThat(user.getUserId()).isEqualTo(userId);
    }

    @DisplayName("설정된 초기 값이 존재할 때 findUserById 메서드를 실행하면 기다하는 값을 가져온다.")
    @Test
    void findUserById() {
        //give
        String userId = "userId1";

        //when
        User user = userDao.findUserById(userId).orElseGet(null);

        //then
        assertThat(user.getUserId()).isEqualTo(userId);
    }

    @DisplayName("설정된 초기 값이 존재할 때 getSize 메서드를 실행하면 User의 개수를 가져온다.")
    @Test
    void getSize() {
        //give
        //when
        int size = userDao.getSize();

        //then
        assertThat(size).isEqualTo(PRECONDITION_USER_LENGTH);
    }

    @DisplayName("설정된 초기값이 존재할때 입력받은 아이디를 가지고 있는 유저의 이름, 이메일 정보를 수정한다.")
    @Test
    void update() {
        //give
        String userId = "userId1";
        String name = "newName";
        String email = "newEmail";

        //when
        userDao.update(userId, name, email);
        User user = userDao.findUserById(userId).orElseGet(null);

        //then
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
    }
}
