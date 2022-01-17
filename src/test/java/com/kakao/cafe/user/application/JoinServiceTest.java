package com.kakao.cafe.user.application;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class JoinServiceTest {
    @InjectMocks
    private JoinService joinService;

    @Mock
    private UserRepository userRepository;

    @ParameterizedTest
    @DisplayName("이미 존재하는 유저일 때 저장 실패")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getUsers")
    public void testFailedWhenExistUser(User user){
        //given
        given(userRepository.getUser(any())).willReturn(Optional.of(user));

        //when
        //then
        assertThatThrownBy(() -> joinService.save(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}