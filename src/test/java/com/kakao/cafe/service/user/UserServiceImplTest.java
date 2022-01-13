package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.user.UserReqDto;
import com.kakao.cafe.dto.user.UserResDto;
import com.kakao.cafe.repository.user.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class UserServiceImplTest {

    MemoryUserRepository memoryUserRepository;
    UserServiceImpl userService;

    @BeforeEach
    void beforeEach(){
        memoryUserRepository = new MemoryUserRepository();
        userService = new UserServiceImpl(memoryUserRepository);
    }

    @AfterEach
    void afterEach(){
        memoryUserRepository.clearUserStore();
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
        assertThrows(IllegalStateException.class, () -> userService.addUser(userReqDto2));
    }

    @DisplayName("등록된 유저 수 확인")
    @Test
    void findUsers() {
        UserReqDto userReqDto = new UserReqDto("test123", "1234", "testName", "test@kakaocorp.com");
        userService.addUser(userReqDto);
        assertThat(userService.findUsers().size()).isEqualTo(1);
    }

    @DisplayName("유저를 id로 탐색")
    @Test
    void findUserById() {
        UserReqDto userReqDto = new UserReqDto("test1234", "1234", "testName", "test@kakaocorp.com");
        userService.addUser(userReqDto);
        assertThat(userService.findUserById(0L).getUserId()).isEqualTo(userReqDto.getUserId());
    }
//
//    @Test
//    void updateUser() {
//    }
}