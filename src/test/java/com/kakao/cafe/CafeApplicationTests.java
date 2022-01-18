package com.kakao.cafe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import org.slf4j.Logger;

@SpringBootTest
class CafeApplicationTests {

	Logger logger = LoggerFactory.getLogger(CafeApplicationTests.class);

	@ParameterizedTest
	@DisplayName("여러 값으로 반복 테스트를 진행한다")
	@ValueSource(ints = {1, 2, 3, 4, 5})
	void readValues(int input) {
		logger.info("ParameterizedTest로부터 온 입력값 {}", input);
		assertThat(input).isGreaterThan(0);
	}
}
