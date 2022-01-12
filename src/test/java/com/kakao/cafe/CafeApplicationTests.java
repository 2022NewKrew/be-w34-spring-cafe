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
<<<<<<< HEAD
<<<<<<< HEAD
	@DisplayName("userController가 null인지 확인")
=======
=======
>>>>>>> 3f9e887 (Test code 수정)
<<<<<<< HEAD
	@DisplayName("userController가 null이 아닌지 확인")
=======
	@DisplayName("userController가 null인지 확인")
>>>>>>> 374a4c5 (H2 DB 연결 없이 기능 구현 완료(로그인 기능 x))
<<<<<<< HEAD
>>>>>>> 2e1613d (H2 DB 연결 없이 기능 구현 완료(로그인 기능 x))
=======
=======
	@DisplayName("userController가 null이 아닌지 확인")
>>>>>>> 31bc618 (Test code 수정)
>>>>>>> 3f9e887 (Test code 수정)
	void userControllerNotNull() {
		assertThat(userController).isNotNull();
	}

	@Test
<<<<<<< HEAD
<<<<<<< HEAD
	@DisplayName("articleController가 null인지 확인")
=======
=======
>>>>>>> 3f9e887 (Test code 수정)
<<<<<<< HEAD
	@DisplayName("articleController가 null이 아닌지 확인")
=======
	@DisplayName("articleController가 null인지 확인")
>>>>>>> 374a4c5 (H2 DB 연결 없이 기능 구현 완료(로그인 기능 x))
<<<<<<< HEAD
>>>>>>> 2e1613d (H2 DB 연결 없이 기능 구현 완료(로그인 기능 x))
=======
=======
	@DisplayName("articleController가 null이 아닌지 확인")
>>>>>>> 31bc618 (Test code 수정)
>>>>>>> 3f9e887 (Test code 수정)
	void articleControllerNotNull() {
		assertThat(articleController).isNotNull();
	}


}
