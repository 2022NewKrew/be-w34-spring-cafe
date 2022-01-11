package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    UserRepository userRepository = UserRepository.getRepository();
    UserService userService = new UserService();

    @BeforeEach
    void init() {
        userRepository.clear();
    }

    @Test
    @DisplayName("유저 생성, 저장, 조회 확인")
    void testUserCreationAndSave() throws Exception {
        // given
        userService.createUser("email1@gmail.com", "춘식이", "12345", LocalDateTime.now());
        userService.createUser("email2@gmail.com", "무지", "abcd", LocalDateTime.now());
        userService.createUser("email3@gmail.com", "라이언", "1q2w3e4r", LocalDateTime.now());

        // when
        User findUser1 = userService.userRepository.find(0L);
        User findUser2 = userService.userRepository.find(1L);
        User findUser3 = userService.userRepository.find(2L);

        // then
        assertThat(findUser1.getId()).isEqualTo(0);
        assertThat(findUser2.getEmail()).isEqualTo("email2@gmail.com");
        assertThat(findUser3.getNickName()).isEqualTo("라이언");

    }

    @Test
    @DisplayName("회원가입 후 뷰 데이터 확인")
    void testViewDataAfterSignUP() throws Exception {
        // given
        userService.createUser("email1@gmail.com", "춘식이", "12345", LocalDateTime.now());

        // when
        GetSignUpResultResponseDTO signUpResultViewDTO = userService.getSignUpResultViewData(0L);

        // then
        assertThat(signUpResultViewDTO.email).isEqualTo("email1@gmail.com");
        assertThat(signUpResultViewDTO.nickName).isEqualTo("춘식이");
    }

    @Test
    @DisplayName("모든 유저 조회 기능 확인")
    void testViewDataForAllUserFind() throws Exception {
        // given
        for (int i = 0; i < 100; i++) {
            userService.createUser("email" + i + "@gmail.com", "춘식이" + i, "12345", LocalDateTime.now());
        }

        // when
        FindAllUserResponseDTO allUserViewDTO1 = userService.getAllUserViewData(0L, 10L);
        FindAllUserResponseDTO allUserViewDTO2 = userService.getAllUserViewData(0L, 0L);
        FindAllUserResponseDTO allUserViewDTO3 = userService.getAllUserViewData(-1L, 5L);
        FindAllUserResponseDTO allUserViewDTO4 = userService.getAllUserViewData(10L, 1L);
        FindAllUserResponseDTO allUserViewDTO5 = userService.getAllUserViewData(0L);
        FindAllUserResponseDTO allUserViewDTO6 = userService.getAllUserViewData(10L, 100L);

        // then
        assertThat(allUserViewDTO1.allUserDataList.size()).isEqualTo(10);
        assertThat(allUserViewDTO2.allUserDataList.size()).isEqualTo(0);
        assertThat(allUserViewDTO3.allUserDataList.size()).isEqualTo(5);
        assertThat(allUserViewDTO4.allUserDataList.size()).isEqualTo(0);
        assertThat(allUserViewDTO5.allUserDataList.size()).isEqualTo(100);
        assertThat(allUserViewDTO6.allUserDataList.get(0).nickName).contains("춘식이10");
        assertThat(allUserViewDTO6.allUserDataList.get(1).email).contains("email11@gmail");

    }

    @Test
    @DisplayName("프로파일 화면 뷰 데이터 확인")
    void testViewDataForProfile() throws Exception {
        // given
        userService.createUser("email1@gmail.com", "춘식이", "12345", LocalDateTime.now());

        // when
        GetProfileResponseDTO profileViewDTO = userService.getUserProfile(0L);

        // then
        assertThat(profileViewDTO.email).isEqualTo("email1@gmail.com");
        assertThat(profileViewDTO.nickName).isEqualTo("춘식이");
}

    @Test
    @DisplayName("공백 DTO 생성 테스트")
    void testEmptyDTO() throws Exception {
        // given
        FindAllUserResponseDTO allUserViewDTO;

        // when
        allUserViewDTO = new FindAllUserResponseDTO(new ArrayList<User>());

        // then
        assertThat(allUserViewDTO.allUserDataList.size()).isEqualTo(0);

    }

}
