package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.repository.UserDao;
import com.kakao.cafe.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
	private UserService userService;
	private UserRepository mockUserRepository;

	public UserServiceTest() {
		mockUserRepository = mock(UserDao.class);
		userService = new UserServiceImpl(mockUserRepository);
	}

	@DisplayName("프로필 업데이트 테스트")
	@Test
	public void updateUserProfileTest() {
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
	public void checkPasswordTest() {
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
	public void checkPasswordIncorrectTest() {
		// given
		User user = new User(1,"testId", "email", "name", "password");
		String userId = "testId";
		String password = "wrongPassword";

		when(mockUserRepository.findByUserId("testId")).thenReturn(user);

		assertThrows(IllegalArgumentException.class, () -> {
			userService.checkPassword(userId, password);
		});
	}
}
