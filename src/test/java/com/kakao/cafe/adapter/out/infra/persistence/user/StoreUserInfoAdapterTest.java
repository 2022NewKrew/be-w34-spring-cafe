package com.kakao.cafe.adapter.out.infra.persistence.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.BDDMockito.given;

import com.kakao.cafe.application.user.dto.SignUpRequest;
import com.kakao.cafe.application.user.dto.UpdateRequest;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import com.kakao.cafe.domain.user.exceptions.UserIdDuplicationException;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StoreUserInfoAdapterTest {

    @Mock
    UserInfoRepository userInfoRepository;

    @InjectMocks
    StoreUserInfoAdapter storeUserInfoAdapter;

    @DisplayName("정상 회원가입 테스트")
    @Test
    void registerNormalUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("champ", "test", "HaChanho", "champ@kakao.com");

        // then
        assertThatNoException().isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("ID 누락 user 회원가입 테스트")
    @Test
    void registerNullUserIdUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("", "test", "HaChanho", "champ@kakao.com");

        // then
        assertThatExceptionOfType(IllegalUserIdException.class)
            .isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("ID 공백 user 회원가입 테스트")
    @Test
    void registerBlankUserIdUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("cham p", "test", "HaChanho", "champ@kakao.com");

        // then
        assertThatExceptionOfType(IllegalUserIdException.class)
            .isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("Password 누락 user 회원가입 테스트")
    @Test
    void registerNullPasswordUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("champ", "", "HaChanho", "champ@kakao.com");

        // then
        assertThatExceptionOfType(IllegalPasswordException.class)
            .isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("Password 공백 user 회원가입 테스트")
    @Test
    void registerBlankPasswordUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("champ", "te st", "HaChanho", "champ@kakao.com");

        // then
        assertThatExceptionOfType(IllegalPasswordException.class)
            .isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("이름 누락 user 회원가입 테스트")
    @Test
    void registerNullNameUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("champ", "test", "", "champ@kakao.com");

        // then
        assertThatExceptionOfType(IllegalUserNameException.class)
            .isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("이름 공백 user 회원가입 테스트")
    @Test
    void registerBlankNameUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("champ", "test", "Ha Chanho", "champ@kakao.com");

        // then
        assertThatExceptionOfType(IllegalUserNameException.class)
            .isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("Email 누락 user 회원가입 테스트")
    @Test
    void registerNullEmailUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("champ", "test", "HaChanho", "");

        // then
        assertThatExceptionOfType(IllegalEmailException.class)
            .isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("Email 공백 user 회원가입 테스트")
    @Test
    void registerBlankEmailUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("champ", "test", "HaChanho", "champ@ kakao.com");

        // then
        assertThatExceptionOfType(IllegalEmailException.class)
            .isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("ID 중복없는 회원가입 테스트")
    @Test
    void registerNoDuplicationUserIdUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("champ", "test", "HaChanho", "champ@kakao.com");
        given(userInfoRepository.findByUserId("champ")).willReturn(Optional.empty());

        // then
        assertThatNoException().isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("ID 중복있는 회원가입 테스트")
    @Test
    void registerDuplicationUserIdUser()
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("champ", "test", "HaChanho", "champ@kakao.com");
        User givenUser = new User.Builder().userId("champ")
                                           .password("champion")
                                           .name("Champion")
                                           .email("Champion@kakao.com")
                                           .build();
        given(userInfoRepository.findByUserId(givenUser.getUserId())).willReturn(Optional.of(UserVO.from(givenUser)));

        // then
        assertThatExceptionOfType(UserIdDuplicationException.class)
            .isThrownBy(() -> storeUserInfoAdapter.registerUser(signUpRequest));
    }

    @DisplayName("ID검색 성공 테스트")
    @Test
    void findUserIdSuccess()
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException, UserNotExistException {
        // given
        String userId = "champ";
        User givenUser = new User.Builder().userId(userId)
                                           .password("test")
                                           .name("HaChanho")
                                           .email("champ@kakao.com")
                                           .build();
        given(userInfoRepository.findByUserId(userId)).willReturn(Optional.of(UserVO.from(givenUser)));

        // when
        UserInfo foundUser = storeUserInfoAdapter.findUserByUserId(userId);

        // then
        assertThat(foundUser).isEqualTo(UserInfo.from(givenUser));
    }

    @DisplayName("ID검색 실패 테스트")
    @Test
    void findUserIdFail() {
        // given
        String userId = "champ";
        given(userInfoRepository.findByUserId(userId)).willReturn(Optional.empty());

        // then
        assertThatExceptionOfType(UserNotExistException.class)
            .isThrownBy(() -> storeUserInfoAdapter.findUserByUserId(userId));
    }

    @DisplayName("정상 업데이트 테스트")
    @Test
    void updateNormalUser()
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException {
        // given
        User givenUser = new User.Builder().userId("champ")
                                           .password("test")
                                           .name("HaChanho")
                                           .email("champ@kakao.com")
                                           .build();
        UpdateRequest updateRequest = new UpdateRequest("kakao", "kakao@kakao.com");
        given(userInfoRepository.findByUserId(givenUser.getUserId())).willReturn(Optional.of(UserVO.from(givenUser)));

        // then
        assertThatNoException().isThrownBy(() -> storeUserInfoAdapter.updateUser(givenUser.getUserId(), updateRequest));
    }

    @DisplayName("이름 누락 user 업데이트 테스트")
    @Test
    void updateNullUserIdUser()
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException {
        // given
        User givenUser = new User.Builder().userId("champ")
                                           .password("test")
                                           .name("HaChanho")
                                           .email("champ@kakao.com")
                                           .build();
        UpdateRequest updateRequest = new UpdateRequest("", "kakao@kakao.com");
        given(userInfoRepository.findByUserId(givenUser.getUserId())).willReturn(Optional.of(UserVO.from(givenUser)));

        // then
        assertThatExceptionOfType(IllegalUserNameException.class).isThrownBy(() -> storeUserInfoAdapter.updateUser(
            givenUser.getUserId(),
            updateRequest
        ));
    }

    @DisplayName("이름 공백 user 업데이트 테스트")
    @Test
    void updateBlankUserIdUser()
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException {
        // given
        User givenUser = new User.Builder().userId("champ")
                                           .password("test")
                                           .name("HaChanho")
                                           .email("champ@kakao.com")
                                           .build();
        UpdateRequest updateRequest = new UpdateRequest("kaka o", "kakao@kakao.com");
        given(userInfoRepository.findByUserId(givenUser.getUserId())).willReturn(Optional.of(UserVO.from(givenUser)));

        // then
        assertThatExceptionOfType(IllegalUserNameException.class).isThrownBy(() -> storeUserInfoAdapter.updateUser(
            givenUser.getUserId(),
            updateRequest
        ));
    }

    @DisplayName("Email 누락 user 업데이트 테스트")
    @Test
    void updateNullEmailUser()
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException {
        // given
        User givenUser = new User.Builder().userId("champ")
                                           .password("test")
                                           .name("HaChanho")
                                           .email("champ@kakao.com")
                                           .build();
        UpdateRequest updateRequest = new UpdateRequest("kakao", "");
        given(userInfoRepository.findByUserId(givenUser.getUserId())).willReturn(Optional.of(UserVO.from(givenUser)));

        // then
        assertThatExceptionOfType(IllegalEmailException.class).isThrownBy(() -> storeUserInfoAdapter.updateUser(
            givenUser.getUserId(),
            updateRequest
        ));
    }

    @DisplayName("Email 공백 user 업데이트 테스트")
    @Test
    void updateBlankEmailUser()
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException {
        // given
        User givenUser = new User.Builder().userId("champ")
                                           .password("test")
                                           .name("HaChanho")
                                           .email("champ@kakao.com")
                                           .build();
        UpdateRequest updateRequest = new UpdateRequest("kakao", "kaka o@kakao.com");
        given(userInfoRepository.findByUserId(givenUser.getUserId())).willReturn(Optional.of(UserVO.from(givenUser)));

        // then
        assertThatExceptionOfType(IllegalEmailException.class)
            .isThrownBy(() -> storeUserInfoAdapter.updateUser(givenUser.getUserId(), updateRequest));
    }

    @DisplayName("존재하지 않는 user 업데이트")
    @Test
    void updateNotExistUser() {
        // given
        String userId = "champ";
        UpdateRequest updateRequest = new UpdateRequest("kakao", "kaka o@kakao.com");
        given(userInfoRepository.findByUserId(userId)).willReturn(Optional.empty());

        // then
        assertThatExceptionOfType(UserNotExistException.class).isThrownBy(() -> storeUserInfoAdapter.updateUser(
            userId,
            updateRequest
        ));
    }
}
