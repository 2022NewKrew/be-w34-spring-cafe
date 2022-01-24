package com.kakao.cafe.repository;

import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@JdbcTest
public class UserRepositoryTest {
    /*
    private final int TEST_USER_COUNT = 5;
    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.userRepository = new UserDao(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    public void setUp() {
        String sql = String.format("insert into %s (%s, %s, %s, %s) values (?, ?, ?, ?)",
                UserDBConstants.TABLE_NAME,
                UserDBConstants.COLUMN_USERID,
                UserDBConstants.COLUMN_EMAIL,
                UserDBConstants.COLUMN_NAME,
                UserDBConstants.COLUMN_PASSWORD);

        for (int i = 0; i < TEST_USER_COUNT; i++) {
            jdbcTemplate.update(sql, "id"+i, "password", "name"+i, "email"+i);
        }
    }

    @AfterEach
    public void tearDown() {
        String sql = String.format("truncate table %s", UserDBConstants.TABLE_NAME);
        jdbcTemplate.execute(sql);
    }

    @DisplayName("id로 유저 찾기 테스트 - 존재하는 case")
    @Test
    public void findByUserIdTest() {
        // given
        String userId = "id0";

        // when
        User user = userRepository.findByUserId(userId);

        // then
        assertThat(user.getUserId()).isEqualTo("id0");
    }

    @DisplayName("id로 유저 찾기 테스트 - 존재하지 않는 case")
    @Test
    public void findByUserIdNotExistTest() {
        // given
        String userId = "wrongId";

        assertThrows(NoSuchElementException.class, () -> {
            userRepository.findByUserId(userId);
        });
    }

    @DisplayName("name으로 유저 찾기 테스트 - 존재하는 case")
    @Test
    public void findByNameTest() {
        // given
        String name = "name0";

        // when
        User user = userRepository.findByName(name);

        // then
        assertThat(user.getName()).isEqualTo("name0");
    }

    @DisplayName("name으로 유저 찾기 테스트 - 존재하지 않는 case")
    @Test
    public void findByNameNotExistTest() {
        // given
        String name = "wrongName";

        assertThrows(NoSuchElementException.class, () -> {
            userRepository.findByName(name);
        });
    }

    @DisplayName("유저 목록 반환 테스트")
    @Test
    public void findAllTest() {
        List<User> userList = userRepository.findAll();

        assertThat(userList.size()).isEqualTo(TEST_USER_COUNT);
    }

    @DisplayName("유저 필드 수정 테스트")
    @Test
    public void updateTest() {
        User user = new User(0, "id0", "newEmail","name","password");

        userRepository.update(user);

        User testUser = userRepository.findByUserId("id0");
        assertThat(testUser.getEmail()).isEqualTo("newEmail");
    }

     */
}
