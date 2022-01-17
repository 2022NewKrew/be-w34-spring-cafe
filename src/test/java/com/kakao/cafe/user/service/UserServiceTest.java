package com.kakao.cafe.user.service;

import com.kakao.cafe.user.repository.UserDBRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserDBRepositoryImpl userRepository;
    @Autowired
    UserService userService;

    @Test
    @DisplayName("유저 생성, 저장, 조회 확인")
    void testUserCreationAndSave() throws Exception {
        // given
//        Long id1 = userService.createUser("myId1", "email1@gmail.com", "춘식이", "12345");
//        Long id2 = userService.createUser("myId2", "email2@gmail.com", "무지", "abcd");
//        Long id3 = userService.createUser("myId3", "email3@gmail.com", "라이언", "1q2w3e4r");

        // when
//        User findUser1 = userRepository.find(id1);
//        User findUser2 = userRepository.find(id2);
//        User findUser3 = userRepository.find(id3);

        // then
//        assertThat(findUser1.getId()).isEqualTo(id1);
//        assertThat(findUser2.getEmail()).isEqualTo("email2@gmail.com");
//        assertThat(findUser3.getNickName()).isEqualTo("라이언");

    }

    @Test
    @DisplayName("회원가입 후 뷰 데이터 확인")
    void testViewDataAfterSignUP() throws Exception {
        // given
        Long id1 = userService.createUser("myId1", "email1@gmail.com", "춘식이", "12345");

        // when
//        GetSignUpResultResponseDTO signUpResultViewDTO = userService.getSignUpResultViewData(id1);

        // then
//        assertThat(signUpResultViewDTO.email).isEqualTo("email1@gmail.com");
//        assertThat(signUpResultViewDTO.nickName).isEqualTo("춘식이");
    }

    @Test
    @DisplayName("모든 유저 조회 기능 확인")
    void testViewDataForAllUserFind() throws Exception {
        // given
//        int numOfUser = userRepository.findAll().size();
//        System.out.println("numOfUser = " + numOfUser);
//        for (int i = 0; i < 100; i++) {
//            userService.createUser("myid"+i, "email" + i + "@gmail.com", "춘식이" + i, "12345");
//        }

        // when
//        AllUserProfileServiceResponse allUserViewDTO1 = userService.getAllUserViewData(0L, 10L);
//        AllUserProfileServiceResponse allUserViewDTO2 = userService.getAllUserViewData(0L, 0L);
//        AllUserProfileServiceResponse allUserViewDTO3 = userService.getAllUserViewData(-1L, 5L);
//        AllUserProfileServiceResponse allUserViewDTO4 = userService.getAllUserViewData(10L, 1L);
//        AllUserProfileServiceResponse allUserViewDTO5 = userService.getAllUserViewData(0L);
//        AllUserProfileServiceResponse allUserViewDTO6 = userService.getAllUserViewData(10L, 100L);

        // then
//        assertThat(allUserViewDTO1.allUserDataList.size()).isEqualTo(10);
//        assertThat(allUserViewDTO2.allUserDataList.size()).isEqualTo(0);
//        assertThat(allUserViewDTO3.allUserDataList.size()).isEqualTo(5);
//        assertThat(allUserViewDTO4.allUserDataList.size()).isEqualTo(0);
//        assertThat(allUserViewDTO5.allUserDataList.size()).isEqualTo(numOfUser + 100);
//        assertThat(allUserViewDTO6.allUserDataList.get(numOfUser + 0).nickName).contains("춘식이" + (75 + numOfUser));
//        assertThat(allUserViewDTO6.allUserDataList.get(numOfUser + 1).email).contains("email"+(74 + numOfUser)+"@gmail");

    }

    @Test
    @DisplayName("프로파일 화면 뷰 데이터 확인")
    void testViewDataForProfile() throws Exception {
        // given
        Long id1 = userService.createUser("myId1", "email1@gmail.com", "춘식이", "12345");

        // when
//        UserProfileResponseDTO profileViewDTO = userService.getUserProfile(id1);

        // then
//        assertThat(profileViewDTO.email).isEqualTo("email1@gmail.com");
//        assertThat(profileViewDTO.nickName).isEqualTo("춘식이");
    }

    @Test
    @DisplayName("공백 DTO 생성 테스트")
    void testEmptyDTO() throws Exception {
        // given
//        AllUserProfileServiceResponse allUserViewDTO;

        // when
//        allUserViewDTO = new AllUserProfileServiceResponse(new ArrayList<User>());

        // then
//        assertThat(allUserViewDTO.allUserDataList.size()).isEqualTo(0);

    }

}
