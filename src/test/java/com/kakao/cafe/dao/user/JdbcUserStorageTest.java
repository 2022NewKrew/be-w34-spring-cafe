package com.kakao.cafe.dao.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.model.user.Email;
import com.kakao.cafe.model.user.Name;
import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserFactory;
import com.kakao.cafe.model.user.UserId;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
        String preSql = "SET REFERENTIAL_INTEGRITY FALSE";
        jdbcTemplate.execute(preSql);
        String sql = "TRUNCATE TABLE USER_DATA";
        jdbcTemplate.execute(sql);
        String postSql = "SET REFERENTIAL_INTEGRITY TRUE";
        jdbcTemplate.execute(postSql);
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
        User user = UserFactory.getUser("userId", "password", "name", "email");

        //when
        userDao.addUser(user);
        user = userDao.findUserById(user.getUserId()).orElseGet(null);

        //then
        assertThat(user.getUserId()).isEqualTo(new UserId("userId"));
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
        User user = UserFactory.getUser("userId1", "password1", "newName", "newEmail");

        //when
        userDao.update(user);
        user = userDao.findUserById(user.getUserId()).orElseGet(null);

        //then
        assertThat(user.getName()).isEqualTo(new Name("newName"));
        assertThat(user.getEmail()).isEqualTo(new Email("newEmail"));
    }
}
