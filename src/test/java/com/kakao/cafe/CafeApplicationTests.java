package com.kakao.cafe;

import com.kakao.cafe.Controller.ArticleController;
import com.kakao.cafe.Controller.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CafeApplicationTests {

	@Autowired
	UserController userController;
	@Autowired
	ArticleController articleController;

	@Test
	@DisplayName("userController가 null이 아닌지 확인")
	void userControllerNotNull() {
		assertThat(userController).isNotNull();
	}

	@Test
	@DisplayName("articleController가 null이 아닌지 확인")
	void articleControllerNotNull() {
		assertThat(articleController).isNotNull();
	}
}
