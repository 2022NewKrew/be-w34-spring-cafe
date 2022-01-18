package com.kakao.cafe.application.user;

import com.kakao.cafe.application.user.validation.NonExistsUserIdException;
import com.kakao.cafe.application.user.validation.UserErrorCode;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserDaoPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
class FindUserServiceTest {

    @InjectMocks
    FindUserService findUserService;

    @Mock
    UserDaoPort userDaoPort;

    @DisplayName("유저 ID로 사용자를 조회할 수 있다")
    @Test
    void checkFindUserByUserId() {
        // given
        User expectedUser = new User("2wls", "0224", "윤이진", "483759@naver.com");
        Optional<User> expectedOptionalUser = Optional.of(expectedUser);
        String userId = "2wls";
        given(userDaoPort.findByUserId(userId))
                .willReturn(expectedOptionalUser);

        // when
        User user = findUserService.findByUserId(userId);

        //then
        verify(userDaoPort).findByUserId(userId);
        assertThat(user)
                .usingRecursiveComparison()
                .isEqualTo(expectedUser);
    }

    @DisplayName("존재하지 않는 유저 ID로 사용자 조회를 할 수 없다")
    @Test
    void checkFindNonExistUserByUserIdException() throws Exception {
        // given
        String userIdThatDoesNotExist = "2wls";
        given(userDaoPort.findByUserId(userIdThatDoesNotExist))
                .willReturn(Optional.empty());

        // when
        NonExistsUserIdException exception = assertThrows(NonExistsUserIdException.class, () -> findUserService.findByUserId(userIdThatDoesNotExist));

        assertThat(exception.getMessage())
                .isEqualTo(UserErrorCode.NON_EXISTS_USER_ID.getMessage());
        verify(userDaoPort).findByUserId(userIdThatDoesNotExist);
    }

    @DisplayName("모든 사용자의 목록을 조회할 수 있다")
    @Test
    void checkFindAllUserList() {
        // given
        List<User> users = List.of(
                new User("2wls", "0224", "윤이진", "483759@naver.com"),
                new User("1234", "1234", "1234", "1234@naver.com")
        );
        given(userDaoPort.findAll())
                .willReturn(users);

        // when
        List<User> userList = findUserService.findAllUser();

        //then
        verify(userDaoPort).findAll();
        assertThat(userList)
                .extracting("userId", "password", "name", "email")
                .containsExactly(
                        tuple("2wls", "0224", "윤이진", "483759@naver.com"),
                        tuple("1234", "1234", "1234", "1234@naver.com")
                );
    }

    @DisplayName("사용자의 아이디와 패스워드가 일치하는지 확인할 수 있다")
    @Test
    void checkUserIdAndPasswordMatch() {
        // given
        String userId = "2wls";
        String password = "0224";
        given(userDaoPort.findByUserIdAndPassword(userId, password))
                .willReturn(Optional.of(new User("2wls", "0224", "윤이진", "483759@naver.com")));

        // when
        boolean passwordMatch = findUserService.checkPassWordMatch(userId, password);

        //then
        assertThat(passwordMatch)
                .isTrue();
        verify(userDaoPort).findByUserIdAndPassword(userId, password);
    }

    @DisplayName("사용자의 아이디와 패스워드가 일치하지 않으면 에러를 반환한다")
    @Test
    void checkUserIdAndPasswordUnMatch() {
        // given
        String userId = "2wls";
        String passwordNotMatch = "1234";
        given(userDaoPort.findByUserIdAndPassword(userId, passwordNotMatch))
                .willReturn(Optional.empty());

        // when
        boolean passwordMatch = findUserService.checkPassWordMatch(userId, passwordNotMatch);

        assertThat(passwordMatch)
                .isFalse();
        verify(userDaoPort).findByUserIdAndPassword(userId, passwordNotMatch);
    }
}