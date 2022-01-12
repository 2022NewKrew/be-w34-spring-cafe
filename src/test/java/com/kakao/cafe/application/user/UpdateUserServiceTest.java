package com.kakao.cafe.application.user;

import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.UpdateUserPort;
import com.kakao.cafe.domain.user.User;
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
    FindUserPort findUserPort;

    @Mock
    UpdateUserPort updateUserPort;

    @DisplayName("사용자의 정보를 수정할 수 있다.")
    @Test
    void checkUserJoin() {
        // given
        UserVo userVo = new UserVo("2wls", "0224", "윤이진", "483759@naver.com");
        given(findUserPort.findByUserId("2wls"))
                .willReturn(Optional.of(userVo.convertVoToEntity()));

        // when
        updateUserService.updateInformation(userVo);

        //then
        verify(updateUserPort).save(any(User.class));
    }

    @DisplayName("존재하지 않는 사용자는 정보를 수정할 수 없다.")
    @Test
    void checkDuplicatedUserJoinException() {
        // given
        UserVo userVo = new UserVo("2wls", "0224", "윤이진", "483759@naver.com");
        given(findUserPort.findByUserId("2wls"))
                .willReturn(Optional.empty());

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> updateUserService.updateInformation(userVo));

        //then
        assertThat(exception.getMessage())
                .isEqualTo("존재하지 않는 사용자의 정보를 수정할 수 없습니다.");
        verify(updateUserPort, never()).save(any(User.class));
    }
}