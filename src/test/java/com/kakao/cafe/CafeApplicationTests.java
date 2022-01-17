package com.kakao.cafe;

import com.kakao.cafe.web.ApiController;
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
    ApiController apiController;

    @Test
    @DisplayName("controllerNotNull???")
    void controllerNotNull() {
        assertThat(apiController).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("test many time")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void readValues(int input) {
        logger.info("parameter value? {}", input);
        assertThat(input).isGreaterThan(0);
    }

}
