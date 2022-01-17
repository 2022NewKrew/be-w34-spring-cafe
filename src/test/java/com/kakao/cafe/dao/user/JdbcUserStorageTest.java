package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.user.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JdbcUserStorage 테스트")
@JdbcTest
class JdbcUserStorageTest {
    private static final int PRECONDITION_USER_LENGTH = 10;

    private final UserDao userDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcUserStorageTest(JdbcTemplate jdbcTemplate) {
        this.userDao = new JdbcUserStorage(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    private void insertInitData() {
        String sql = "INSERT INTO USER_DATA(USER_ID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
        for (int i = 1; i <= 10; i++) {
            jdbcTemplate.update(sql, "userId" + i, "password" + i, "name" + i, "email" + i);
        }
    }

    @AfterEach
    private void deleteInitData() {
        String sql = "TRUNCATE TABLE USER_DATA";
        jdbcTemplate.execute(sql);
    }

    @DisplayName("설정된 초기 값이 존재할 때 getUsers 메서드를 실행하면 저장한 모든 User를 가져온다.")
    @Test
    public void getUsers() {
        //give
        UserId userId = new UserId("userId1");
        //when
        List<User> users = userDao.getUsers();
        //then
        assertThat(users.size()).isEqualTo(PRECONDITION_USER_LENGTH);
        assertThat(users.get(0).getUserId()).isEqualTo(userId);
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

    @DisplayName("설정된 초기 값이 존재할 때 findUserById 메서드를 실행하면 기대하는 값을 가져온다.")
    @Test
    public void findUserById() {
        //give
        UserId userId = new UserId("userId1");

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
        assertThat(size).isEqualTo(PRECONDITION_USER_LENGTH);
    }

    @DisplayName("설정된 초기값이 존재할때 입력받은 아이디를 가지고 있는 유저의 이름, 이메일 정보를 수정한다.")
    @Test
    public void update() {
        //give
        UserId userId = new UserId("userId1");
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
