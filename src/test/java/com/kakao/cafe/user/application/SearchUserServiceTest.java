package com.kakao.cafe.user.application;

import com.kakao.cafe.user.data.UsersData;
import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SearchUserServiceTest {
    @InjectMocks
    private SearchUserService searchUserService;

    @Mock
    private UserRepository userRepository;


    @Test
    @DisplayName("모든 유저 가져오기 성공")
    public void successGetAllUsers(){
        //given
        List<User> users = UsersData.getUserList();
        given(userRepository.getAllUsers()).willReturn(users);

        //when
        List<User> actualUsers = searchUserService.getAllUsers();

        //then
        assertThat(actualUsers).isEqualTo(users);
    }


    @ParameterizedTest
    @DisplayName("한 유저 가져오기 성공")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getUsers")
    public void successGetOneUser(User user){
        //given
        given(userRepository.getUser(user.getUserId())).willReturn(Optional.of(user));

        //when
        User actualUser = searchUserService.getUser(user.getUserId());

        //then
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    @DisplayName("한 유저 가져오기 실패")
    public void failedGetOneUser(){
        //given
        User user = UsersData.getUserList().get(0);
        given(userRepository.getUser(user.getUserId())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> searchUserService.getUser(user.getUserId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("없습니다.");
    }
}