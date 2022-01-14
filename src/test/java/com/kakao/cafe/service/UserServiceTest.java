package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.exception.AlreadyExistUserException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository jdbcUserRepositoryImpl;

    @InjectMocks
    private UserService userService;

    @DisplayName("createUser 테스트 - CreateUserProfileRequest가 있고 같은 유저아이디가 존재하지 않을 때 jdbcUserRepositoryImpl.save가 1번 발생")
    @Test
    void createUser_CreateUserProfileRequestAndNotExistSameIdUser_VerifyOneTimeJdbcUserRepositoryImplSave() {
        //given
        UserDto.CreateUserProfileRequest createUserProfileRequest = new UserDto.CreateUserProfileRequest();
        createUserProfileRequest.setUserId("test");
        createUserProfileRequest.setEmail("test@daum.net");
        createUserProfileRequest.setName("test name");
        createUserProfileRequest.setPassword("test password");

        when(jdbcUserRepositoryImpl.findByUserId(any())).thenReturn(Optional.empty());

        // when
        userService.createUser(createUserProfileRequest);

        // then
        verify(jdbcUserRepositoryImpl, times(1)).save(any());
    }

    @DisplayName("createUser 테스트 - CreateUserProfileRequest가 있고 같은 유저아이디가 존재할때 AlreadyEixstUser Exception Throw")
    @Test
    void createUser_CreateUserProfileRequestAndExistSameIdUser_ThrowAlreadyExistUserException() {
        //given
        UserDto.CreateUserProfileRequest createUserProfileRequest = new UserDto.CreateUserProfileRequest();
        createUserProfileRequest.setUserId("test");
        createUserProfileRequest.setEmail("test@daum.net");
        createUserProfileRequest.setName("test name");
        createUserProfileRequest.setPassword("test password");

        User user = new User("test", "test", "test", "test");

        when(jdbcUserRepositoryImpl.findByUserId(any())).thenReturn(Optional.of(user));

        // when // then
        Assertions.assertThrows(AlreadyExistUserException.class, () -> {
            userService.createUser(createUserProfileRequest);
        });
    }

    @DisplayName("readUsers 테스트 - 두명의 유저가 있을경우, UserProfileResponseList 사이즈가 2이고, Dto와 Entity의 이름과 아이디가 같다")
    @Test
    void readUsers_TwoSizeOfUserList_UserProfileResponseListSize2AndSameWithDtoAndEntityUserIdAndName() {
        // given
        User user1 = new User("test1", "test1 password", "test1 name", "test1@daum.net");
        User user2 = new User("test2", "test2 password", "test2 name", "test2@daum.net");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(jdbcUserRepositoryImpl.findAll()).thenReturn(users);

        // when
        List<UserDto.UserProfileResponse> result = userService.readUsers();

        // then
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(user1.getUserId(), result.get(0).getUserId());
        Assertions.assertEquals(user2.getUserId(), result.get(1).getUserId());
        Assertions.assertEquals(user1.getName(), result.get(0).getName());
        Assertions.assertEquals(user2.getName(), result.get(1).getName());
    }

    @DisplayName("readUser 테스트 - 유저가 존재하는 경우 Entity와 Dto의 이름과 유저 아이디가 같다")
    @Test
    void readUser_ExistUser_SameWithEntityAndDtoNameAndUserId() {
        // given
        User user = new User("test", "test password", "test name", "test@daum.net");

        when(jdbcUserRepositoryImpl.findByUserId("test")).thenReturn(Optional.of(user));

        // when
        UserDto.UserProfileResponse result = userService.readUser("test");

        // then
        Assertions.assertEquals(user.getName(), result.getName());
        Assertions.assertEquals(user.getUserId(), result.getUserId());
    }

    @DisplayName("readUser 테스트 - 유저가 존재하지 않는 경우 UserNotFoundException Throw")
    @Test
    void readUser_NotExistUser_ThrowUserNotFoundException() {
        // given
        when(jdbcUserRepositoryImpl.findByUserId("test")).thenReturn(Optional.empty());

        // when // then
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.readUser("test");
        });
    }

    @DisplayName("updateUser 테스트 - 사용자가 존재하고 비밀번호가 일치할때, jdbcUserRepository.save 가 1번 실행")
    @Test
    void updateUser_ExistUserAndPasswordIsCorrect_VerifyOneTimeJdbcUserRepositorySave() {
        // given
        String userId = "test";
        UserDto.UpdateUserProfileRequest updateUserProfileRequest = new UserDto.UpdateUserProfileRequest();
        updateUserProfileRequest.setName("test name2");
        updateUserProfileRequest.setEmail("test2@daum.net");
        updateUserProfileRequest.setOriginPassword("test password");
        updateUserProfileRequest.setChangedPassword("changed password");

        User user = new User("test", "test password", "test name", "test@daum.net");

        when(jdbcUserRepositoryImpl.findByUserId("test")).thenReturn(Optional.of(user));

        // when
        userService.updateUser(userId, updateUserProfileRequest);

        // then
        verify(jdbcUserRepositoryImpl, times(1)).save(any());
    }

    @DisplayName("updateUser 테스트 - 사용자가 존재하지 않을 때 UserNotFoundException 발생")
    @Test
    void updateUser_NotExistUser_ThrowUserNotFoundException() {
        // given
        String userId = "test";
        UserDto.UpdateUserProfileRequest updateUserProfileRequest = new UserDto.UpdateUserProfileRequest();
        updateUserProfileRequest.setName("test name");
        updateUserProfileRequest.setEmail("test@daum.net");
        updateUserProfileRequest.setOriginPassword("test password");
        updateUserProfileRequest.setChangedPassword("changed password");

        when(jdbcUserRepositoryImpl.findByUserId("test")).thenReturn(Optional.empty());

        // when // then
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(userId, updateUserProfileRequest);
        });
    }

    @DisplayName("updateUser 테스트 - 비밀번호가 잘못된 경우 IllegalArgumentException Throw")
    @Test
    void updateUser_NotCorrectPassword_ThrowIllegalArgumentException() {
        // given
        String userId = "test";
        UserDto.UpdateUserProfileRequest updateUserProfileRequest = new UserDto.UpdateUserProfileRequest();
        updateUserProfileRequest.setName("test name");
        updateUserProfileRequest.setEmail("test@daum.net");
        updateUserProfileRequest.setOriginPassword("incorrect test password");
        updateUserProfileRequest.setChangedPassword("changed password");

        User user = new User("test", "test password", "test name", "test@daum.net");

        when(jdbcUserRepositoryImpl.findByUserId("test")).thenReturn(Optional.of(user));

        // when // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.updateUser(userId, updateUserProfileRequest);
        });
    }

}
