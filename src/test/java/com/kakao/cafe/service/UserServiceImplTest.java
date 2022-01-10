package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserJoinDTO;
import com.kakao.cafe.error.exception.duplication.UserEmailDuplicationException;
import com.kakao.cafe.error.exception.duplication.UserNickNameDuplicationException;
import com.kakao.cafe.error.exception.nonexist.UserNotFoundedException;
import com.kakao.cafe.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

/**
 * Test For {@code UserServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    private User userInRepo;

    @BeforeEach
    void setUp() {
        userInRepo = User.builder()
                .id(Long.valueOf(1))
                .email("gallix@kakao.com")
                .password("abcd1234!")
                .nickName("gallix")
                .build();
    }

    @Test
    @DisplayName("id 로 User 찾기 -> 존재 하지 않음")
    void findById_nonExist() {
        //Given
        Long nonExistId = Long.valueOf(142);
        given(userRepository.findById(nonExistId)).willReturn(Optional.empty());

        //When, Then
        assertThrows(UserNotFoundedException.class, () -> userService.findById(nonExistId));
    }

    @Test
    @DisplayName("id 로 User 찾기 -> 존재함")
    void findById() {
        //Given
        Long existId = userInRepo.getId();
        given(userRepository.findById(existId)).willReturn(Optional.of(userInRepo));

        //When
        User result = userService.findById(existId);

        //Then
        assertEquals(userInRepo, result);
    }

    @Test
    @DisplayName("id로 user 존재하는 여부 확인 -> 존재하지 않음")
    void existsById_false() {
        //Given
        Long nonExistId = Long.valueOf(142);
        given(userRepository.existsById(nonExistId)).willReturn(false);

        //When
        boolean result = userService.existsById(nonExistId);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("id로 user 존재하는 여부 확인 -> 존재")
    void existsById_true() {
        //Given
        Long existId = userInRepo.getId();
        given(userRepository.existsById(existId)).willReturn(true);

        //When
        boolean result = userService.existsById(existId);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("유저 추가 -> 이미 존재하는 email")
    void join_alreadyExistEmail() {
        //Given
        String alreadyExistEmail = userInRepo.getEmail();
        String nonExistNickName = "nonExist";
        given(userRepository.existsByEmail(alreadyExistEmail)).willReturn(true);

        UserJoinDTO dto = getUserJoinDTOInstance(alreadyExistEmail, nonExistNickName);

        //When, Then
        assertThrows(UserEmailDuplicationException.class, () -> userService.join(dto));

        then(userRepository).should(never()).save(any());
    }

    private

    @Test
    @DisplayName("유저 추가 -> 이미 존재하는 nickName")
    void join_alreadyExistNickName() {
        //Given
        String nonExistEmail = "nonExist@kakao.com";
        String alreadyExistNickName = userInRepo.getNickName();
        given(userRepository.existsByEmail(nonExistEmail)).willReturn(false);
        given(userRepository.existsByNickName(alreadyExistNickName)).willReturn(true);

        UserJoinDTO dto = getUserJoinDTOInstance(nonExistEmail, alreadyExistNickName);

        //When, Then
        assertThrows(UserNickNameDuplicationException.class, () -> userService.join(dto));

        then(userRepository).should(never()).save(any());
    }

    @Test
    @DisplayName("유저 추가 -> 정상")
    void join() {
        //Given
        String nonExistEmail = "nonExist@kakao.com";
        String nonExistNickName = "nonExist";
        given(userRepository.existsByEmail(nonExistEmail)).willReturn(false);
        given(userRepository.existsByNickName(nonExistNickName)).willReturn(false);

        UserJoinDTO dto = getUserJoinDTOInstance(nonExistEmail, nonExistNickName);

        //When
        userService.join(dto);

        //Then
        then(userRepository).should(times(1)).save(any(User.class));
    }

    private UserJoinDTO getUserJoinDTOInstance(String email, String nickName) {
        return UserJoinDTO.builder()
                .email(email)
                .nickName(nickName)
                .password("abcd1234!")
                .build();
    }
}