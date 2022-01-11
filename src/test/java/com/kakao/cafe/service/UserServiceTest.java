package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserUpdateRequest;
import com.kakao.cafe.repository.InMemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class UserServiceTest {

    UserService userService = new UserService();
    UserRepository userRepository = new InMemoryUserRepository();

    @Test
    @DisplayName("회원정보 수정 테스트")
    void updateUser(){
        //given
        User user = User.builder()
                .nickname("nick")
                .name("origin")
                .password("pass")
                .email("a@com").build();
        userRepository.save(user);

        UserUpdateRequest userDTO = new UserUpdateRequest();
        userDTO.setNickname("nick");
        userDTO.setOriginPassword("pass");
        userDTO.setName("new-name");

        //when
        userService.updateUser(userDTO);

        //then
        assertNotEquals("origin", userRepository.findByNickname("nick").get().getName());

    }
}