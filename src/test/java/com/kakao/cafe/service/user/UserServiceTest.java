package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.model.user.UserDto;
import com.kakao.cafe.model.user.UserSignupRequest;
import com.kakao.cafe.model.user.UserUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@Slf4j
@SpringBootTest
class UserServiceTest {
    private static final int INIT_SIZE_OF_USERS = 3; // 데이터베이스에 저장된 초기 회원의 수

    @Autowired
    private UserService userService;
    @Qualifier("userJdbcRepositoryImpl")
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup() {
        String email = "abc1234@abc.com";
        String nickname = "ABC";
        String password = "abcdef";
        LocalDateTime createdAt = LocalDateTime.now();
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .createdAt(createdAt)
                .build();
        userRepository.save(user);
        this.user = userRepository.findByEmail(email).orElse(null);
    }

    @DisplayName("정상적으로 회원가입을 진행하면 에러가 발생하지 않아야 한다.")
    @Test
    void signupUser() {
        String email = "test@test.com";
        String nickname = "테스터";
        String password = "1234";
        UserSignupRequest user = UserSignupRequest.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();

        assertThatNoException().isThrownBy(() -> {
            userService.signupUser(user);
        });
    }

    @DisplayName("정상적으로 회원가입을 진행하면 회원가입 일자가 null이 아니여야 한다.")
    @Test
    void signupUserForCheckingCreatedAt() {
        String email = "test@test.com";
        String nickname = "테스터";
        String password = "1234";
        UserSignupRequest user = UserSignupRequest.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();

        userService.signupUser(user);
        User signupUser = userRepository.findByEmail(email).orElse(null);

        assertThat(signupUser).isNotNull();
        assertThat(signupUser.getCreatedAt()).isNotNull();
    }

    @DisplayName("이미 등록된 이메일을 이용하여 회원가입을 진행하면 에러가 발생한다.")
    @Test
    void signupUserWithDuplicatedEmail() {
        String email = "test@test.com";
        User user = User.builder()
                .email(email)
                .nickname("테스터1")
                .password("1234")
                .build();
        userRepository.save(user);

        UserSignupRequest userWithDuplicatedEmail = UserSignupRequest.builder()
                .email(email)
                .nickname("테스터2")
                .password("1234")
                .build();

        assertThatIllegalArgumentException().isThrownBy(() -> {
            userService.signupUser(userWithDuplicatedEmail);
        });
    }

    @DisplayName("회원 목록의 크기는 등록된 회원의 수와 동일해야 한다.")
    @Test
    void getAllUsers() {
        int size = 30;
        for (int i = 0; i < size; i++) {
            User user = User.builder()
                    .email("test-" + i + "@test.com")
                    .nickname("테스터" + i)
                    .password("1234")
                    .build();
            userRepository.save(user);
        }

        List<UserDto> users = userService.getAllUsers();
        assertThat(users.size()).isEqualTo(size + INIT_SIZE_OF_USERS + 1);
    }

    @DisplayName("id를 이용하여 조회한 회원 정보는 등록된 회원 정보와 같아야 한다.")
    @Test
    void getUserById() {
        long id = user.getId();

        UserDto foundUser = userService.getUserById(id);
        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(foundUser.getNickname()).isEqualTo(user.getNickname());
        assertThat(foundUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(foundUser.getCreatedAt()).isEqualTo(user.getCreatedAt());
    }

    @DisplayName("등록되지 않은 회원 정보를 조회하면 에러를 발생시켜야 한다.")
    @Test
    void getUserByIdNotSignup() {
        long id = 11234L;

        assertThatIllegalArgumentException().isThrownBy(() -> {
           UserDto user = userService.getUserById(id);
        });
    }

    @DisplayName("등록된 사용자의 정보를 업데이트 요청하면 id와 회원가입 일자를 제외한 다른 정보들은 업데이트되어야 한다.")
    @Test
    void updateUser() {
        String updatedEmail = "test@test.com";
        String updatedNickname = "TEST";
        String updatedPassword = "test";
        UserUpdateRequest updateRequest = UserUpdateRequest.builder()
                .email(updatedEmail)
                .nickname(updatedNickname)
                .currentPassword(user.getPassword())
                .password(updatedPassword)
                .build();
        long id = user.getId();

        userService.updateUser(user.getId(), updateRequest);
        User updatedUser = userRepository.findById(id).orElse(null);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(user.getId());
        assertThat(updatedUser.getCreatedAt()).isEqualTo(updatedUser.getCreatedAt());
        assertThat(updatedUser.getEmail()).isEqualTo(updatedEmail);
        assertThat(updatedUser.getNickname()).isEqualTo(updatedNickname);
        assertThat(updatedUser.getPassword()).isEqualTo(updatedPassword);
        assertThat(updatedUser.getUpdatedAt()).isNotNull();
    }

    @DisplayName("등록되지 않은 사용자의 정보를 업데이트 요청하면 에러를 발생시켜야 한다.")
    @Test
    void updateUserNotSignup() {
        String updatedEmail = "test@test.com";
        String updatedNickname = "TEST";
        String updatedPassword = "test";
        UserUpdateRequest updateRequest = UserUpdateRequest.builder()
                .email(updatedEmail)
                .nickname(updatedNickname)
                .currentPassword(user.getPassword())
                .password(updatedPassword)
                .build();
        long id = user.getId() + 1;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            userService.updateUser(id, updateRequest);
        });
    }

    @DisplayName("입력한 비밀번호와 현재 비밀번호가 다르면 에러를 발생시켜야 한다.")
    @Test
    void updateUserNotEqualCurrentPassword() {
        String updatedEmail = "test@test.com";
        String updatedNickname = "TEST";
        String updatedPassword = "test";
        UserUpdateRequest updateRequest = UserUpdateRequest.builder()
                .email(updatedEmail)
                .nickname(updatedNickname)
                .currentPassword(user.getPassword() + 1)
                .password(updatedPassword)
                .build();
        long id = user.getId();

        assertThatIllegalArgumentException().isThrownBy(() -> {
            userService.updateUser(id, updateRequest);
        });
    }

    @DisplayName("입력한 이메일이 본인의 이전 이메일이 아닌 다른 등록된 이메일과 중복된다면 에러를 발생시켜야 한다.")
    @Test
    void updateUserDuplicatedEmail() {
        String email = "test@test.com";
        User anotherUser = User.builder()
                .email(email)
                .nickname("테스터")
                .password("1234")
                .build();
        userRepository.save(anotherUser);
        String updatedEmail = email;
        String updatedNickname = "TEST";
        String updatedPassword = "test";
        UserUpdateRequest updateRequest = UserUpdateRequest.builder()
                .email(updatedEmail)
                .nickname(updatedNickname)
                .currentPassword(user.getPassword())
                .password(updatedPassword)
                .build();
        long id = user.getId();

        assertThatIllegalArgumentException().isThrownBy(() -> {
            userService.updateUser(id, updateRequest);
        });
    }

    @DisplayName("입력한 이메일이 이전의 본인의 이메일이라면 에러가 발생하지 않아야 한다.")
    @Test
    void updateUserSameWithPrevEmail() {
        String updatedEmail = user.getEmail();
        String updatedNickname = "TEST";
        String updatedPassword = "test";
        UserUpdateRequest updateRequest = UserUpdateRequest.builder()
                .email(updatedEmail)
                .nickname(updatedNickname)
                .currentPassword(user.getPassword())
                .password(updatedPassword)
                .build();
        long id = user.getId();

        userService.updateUser(id, updateRequest);
    }
}
