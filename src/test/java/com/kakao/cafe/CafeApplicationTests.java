package com.kakao.cafe;

import com.kakao.cafe.web.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CafeApplicationTests {
	Logger logger = LoggerFactory.getLogger(CafeApplicationTests.class);

	@Autowired
	UserController userController;

	@Test
	void controllerNotNull(){
		assertThat(userController).isNotNull();
	}


	@ParameterizedTest
	@DisplayName("test 진행")
	@ValueSource(strings = {"j","just","justin"})
	void makeUserTest(String string){
		logger.info(string);
	}


}
