package com.kakao.cafe.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsersTest {

    private Users users;

    @BeforeEach
    void setUp() {
        users = new Users();
    }

    @Test
    void add_Invoked_SetFieldOfUserCorrectly() {
        User base = new User(new UserName("testUserName"), new Password("testPassword"), new Name("testName"), new Email("test@email.com"));
        users.add(base);

        assertThat(users.getUserList().get(0).getId()).isNotNull();
    }

    @Test
    void isUserDuplicated_UsernameDuplicated_ReturnsTrue() {
        User base = new User(new UserName("testUserName"), new Password("testPassword"), new Name("testName"), new Email("test@email.com"));
        users.add(base);

        User toFind = new User(new UserName("testUserName"), new Password("newTestPassword"), new Name("newTestName"), new Email("newTest@email.com"));
        assertThat(users.isUserDuplicated(toFind)).isTrue();
    }

    @Test
    void isUserDuplicated_UsernameNotDuplicated_ReturnsFalse() {
        User base = new User(new UserName("testUserName"), new Password("testPassword"), new Name("testName"), new Email("test@email.com"));
        users.add(base);

        User toFind = new User(new UserName("newTestUserName"), new Password("newTestPassword"), new Name("newTestName"), new Email("newTest@email.com"));
        assertThat(users.isUserDuplicated(toFind)).isFalse();
    }

    @Test
    void findById_UserExists_ReturnsCorrectOptional() {
        User base1 = new User(new UserName("testUserName1"), new Password("testPassword1"), new Name("testName1"), new Email("test1@email.com"));
        User base2 = new User(new UserName("testUserName2"), new Password("testPassword2"), new Name("testName2"), new Email("test2@email.com"));
        users.add(base1);
        users.add(base2);

        UUID idToFind = base1.getId();
        Optional<User> actual = users.findById(idToFind);

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getUserName().getValue()).isEqualTo(base1.getUserName().getValue());
        assertThat(actual.get().getPassword().getValue()).isEqualTo(base1.getPassword().getValue());
        assertThat(actual.get().getName().getValue()).isEqualTo(base1.getName().getValue());
        assertThat(actual.get().getEmail().getValue()).isEqualTo(base1.getEmail().getValue());
    }

    @Test
    void findById_UserNotExist_ReturnsEmptyOptional() {
        User base = new User(new UserName("testUserName"), new Password("testPassword"), new Name("testName"), new Email("test@email.com"));
        users.add(base);

        UUID idToFind;
        do {
            idToFind = UUID.randomUUID();
        } while (idToFind.equals(base.getId()));
        Optional<User> actual = users.findById(idToFind);

        assertThat(actual.isPresent()).isFalse();
    }

    @Test
    void findByUserName_UserExists_ReturnsCorrectOptional() {
        User base1 = new User(new UserName("testUserName1"), new Password("testPassword1"), new Name("testName1"), new Email("test1@email.com"));
        User base2 = new User(new UserName("testUserName2"), new Password("testPassword2"), new Name("testName2"), new Email("test2@email.com"));
        users.add(base1);
        users.add(base2);

        UserName userNameToFind = base1.getUserName();
        Optional<User> actual = users.findByUserName(userNameToFind);

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getUserName().getValue()).isEqualTo(base1.getUserName().getValue());
        assertThat(actual.get().getPassword().getValue()).isEqualTo(base1.getPassword().getValue());
        assertThat(actual.get().getName().getValue()).isEqualTo(base1.getName().getValue());
        assertThat(actual.get().getEmail().getValue()).isEqualTo(base1.getEmail().getValue());
    }

    @Test
    void findByUserName_UserNotExist_ReturnsEmptyOptional() {
        User base = new User(new UserName("testUserName"), new Password("testPassword"), new Name("testName"), new Email("test@email.com"));
        users.add(base);

        UserName userNameToFind = new UserName("NotExistingUser");
        Optional<User> actual = users.findByUserName(userNameToFind);

        assertThat(actual.isPresent()).isFalse();
    }
}
