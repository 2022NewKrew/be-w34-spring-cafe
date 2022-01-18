package com.kakao.cafe;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.repository.UserDao;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
	private UserService userService;
	private UserRepository mockUserRepository = mock(UserDao.class);
	@BeforeEach
	private void setUp() {
		userService = new UserServiceImpl(mockUserRepository);
	}

	@DisplayName("프로필 업데이트 테스트")
	@Test
	void updateUserProfileTest() {
		// given
		User user = new User(1,"testId", "email", "name", "password");
		UserProfileDto newProfile = new UserProfileDto("testId", "email2", "name");
		String password = "password";

		when(mockUserRepository.findByUserId("testId")).thenReturn(user);

		// when
		userService.updateUserProfile(newProfile, password);

		// then
		verify(mockUserRepository, times(1)).update(any(User.class));
	}

	@DisplayName("비밀번호 확인 테스트 - 비밀번호 일치 case")
	@Test
	void checkPasswordTest() {
		// given
		User user = new User(1,"testId", "email", "name", "password");
		String userId = "testId";
		String password = "password";

		when(mockUserRepository.findByUserId("testId")).thenReturn(user);

		// when
		userService.checkPassword(userId, password);

		// then - exception 발생 시 fail
	}

	@DisplayName("비밀번호 확인 테스트 - 비밀번호 불일치 case")
	@Test
	void checkPasswordIncorrectTest() {
		// given
		User user = new User(1,"testId", "email", "name", "password");
		String userId = "testId";
		String password = "wrongPassword";

		when(mockUserRepository.findByUserId("testId")).thenReturn(user);

		// when
		try {
			userService.checkPassword(userId, password);

			// then - exception 발생하지 않을 시 fail
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
}
