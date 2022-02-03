package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.user.UserRequest;
import com.kakao.cafe.dto.user.UserResponse;
import com.kakao.cafe.dto.user.UserUpdateReqDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class JdbcUserServiceImplTest {

//    @Autowired
//    UserServiceImpl userService;
//
//    @DisplayName("사용자 추가 확인")
//    @Test
//    void addUser() {
//        UserRequest userRequest = new UserRequest("test", "1234", "testName", "test@kakaocorp.com");
//
//        userService.addUser(userRequest);
//
//        UserResponse userResponse = userService.findUserById(2L);
//        assertThat(userRequest.getName()).isEqualTo(userResponse.getName());
//
//    }
//
//    @DisplayName("중복 아이디 예외 확인")
//    @Test
//    void validateException(){
//        UserRequest userRequest1 = new UserRequest("test11", "1234", "testName", "test@kakaocorp.com");
//        UserRequest userRequest2 = new UserRequest("test11", "1335", "testName2", "test2@kakaocorp.com");
//
//        userService.addUser(userRequest1);
//        assertThrows(IllegalStateException.class, () -> userService.addUser(userRequest2));
//    }
//
//    @DisplayName("등록된 유저 수 확인")
//    @Test
//    void findUsers() {
//        UserRequest userRequest = new UserRequest("test123", "1234", "testName", "test@kakaocorp.com");
//        userService.addUser(userRequest);
//        assertThat(userService.findUsers().size()).isEqualTo(2);
//    }
//
//    @DisplayName("잘못된 id로 유저 탐색")
//    @Test
//    void findUserByIdException() {
//        UserRequest userRequest = new UserRequest("test1234", "1234", "testName", "test@kakaocorp.com");
//        userService.addUser(userRequest);
//        assertThrows(NullPointerException.class, () -> userService.findUserById(4L));
//    }
//
//    @DisplayName("올바른 id로 유저 탐색")
//    @Test
//    void findUserById() {
//        assertThat(userService.findUserById(1L).getUserId()).isEqualTo("admin");
//    }
//
//    @DisplayName("사용자 정보 업데이트 확인")
//    @Test
//    void updateUser() {
//        UserUpdateReqDto userUpdateReqDto = new UserUpdateReqDto(1L, "1234", "12345", "testName2", "test2@kakaocorp.com");
//        userService.modifyUser(userUpdateReqDto);
//        assertThat(userService.findUserById(1L).getName()).isEqualTo("testName2");
//        assertThat(userService.findUserById(1L).getEmail()).isEqualTo("test2@kakaocorp.com");
//        assertThat(userService.findUserById(1L).getPassword()).isEqualTo("12345");
//    }
//
//    @DisplayName("사용자 정보 업데이트 예외")
//    @Test
//    void updateUserException() {
//        UserRequest userRequest = new UserRequest("test1234", "1234", "testName", "test@kakaocorp.com");
//        userService.addUser(userRequest);
//        UserUpdateReqDto userUpdateReqDto = new UserUpdateReqDto(10L, "1234", "12345", "testName2", "test2@kakaocorp.com");
//        assertThrows(NullPointerException.class, ()->userService.modifyUser(userUpdateReqDto));
//    }
}
