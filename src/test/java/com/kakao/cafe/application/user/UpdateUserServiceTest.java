package com.kakao.cafe.application.user;

import com.kakao.cafe.application.user.validation.NonExistsUserIdException;
import com.kakao.cafe.application.user.validation.UserErrorCode;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserDaoPort;
import com.kakao.cafe.domain.user.UserVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class UpdateUserServiceTest {

    @InjectMocks
    UpdateUserService updateUserService;

    @Mock
    UserDaoPort userDaoPort;

    @DisplayName("사용자의 정보를 수정할 수 있다.")
    @Test
    void checkUserJoin() {
        // given
        UserVo userVo = new UserVo("2wls", "0224", "윤이진", "483759@naver.com");
        given(userDaoPort.findByUserId("2wls"))
                .willReturn(Optional.of(userVo.convertVoToEntity()));

        // when
        updateUserService.updateInformation(userVo);

        //then
        verify(userDaoPort).update(any(User.class));
    }

    @DisplayName("존재하지 않는 사용자는 정보를 수정할 수 없다.")
    @Test
    void checkDuplicatedUserJoinException() {
        // given
        UserVo userVo = new UserVo("2wls", "0224", "윤이진", "483759@naver.com");
        given(userDaoPort.findByUserId("2wls"))
                .willReturn(Optional.empty());

        // when
        NonExistsUserIdException exception = assertThrows(NonExistsUserIdException.class, () -> updateUserService.updateInformation(userVo));

        //then
        assertThat(exception.getMessage())
                .isEqualTo(UserErrorCode.NON_EXISTS_USER_ID.getMessage());
        verify(userDaoPort, never()).update(any(User.class));
    }
}