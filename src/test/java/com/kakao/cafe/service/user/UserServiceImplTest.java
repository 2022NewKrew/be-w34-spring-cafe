package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.user.UserReqDto;
import com.kakao.cafe.dto.user.UserResDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    private final UserService userService;

    @Autowired
    UserServiceImplTest(UserService userService) {
        this.userService = userService;
    }


    @DisplayName("사용자 추가 확인")
    @Test
    void addUser() {
        UserReqDto userReqDto = new UserReqDto("test", "1234", "testName", "test@kakaocorp.com");

        userService.addUser(userReqDto);

        UserResDto userResDto = userService.findUserById(1L);
        assertThat(userReqDto.getName()).isEqualTo(userResDto.getName());

    }

    @DisplayName("중복 아이디 예외 확인")
    @Test
    void validateException(){
        UserReqDto userReqDto1 = new UserReqDto("test11", "1234", "testName", "test@kakaocorp.com");
        UserReqDto userReqDto2 = new UserReqDto("test11", "1335", "testName2", "test2@kakaocorp.com");

        userService.addUser(userReqDto1);
        try{
            userService.addUser(userReqDto2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
    }

//    @Test
//    void findUsers() {
//    }
//
//    @Test
//    void findUserById() {
//    }
//
//    @Test
//    void updateUser() {
//    }
}