package com.kakao.cafe;

import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

@SpringBootTest
class UserServiceTest {

	@Test
	void mockUpdateUserProfile() {
		UserRepository mockUserDao = mock(UserRepository.class);

		UserServiceImpl userService = new UserServiceImpl(mockUserDao);


	}

}
