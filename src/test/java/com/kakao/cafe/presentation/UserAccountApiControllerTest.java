package com.kakao.cafe.presentation;

import com.kakao.cafe.user.adapter.in.web.dto.request.UserAccountEnrollRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAccountApiControllerTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @LocalServerPort
    int port;

    void clearDb() {
        jdbcTemplate.update("truncate table user_account");
        jdbcTemplate.update("alter table user_account alter column user_account_id restart with 1");
    }

    @BeforeEach
    void setUp() {
        clearDb();
    }

    @AfterEach
    void tearDown() {
        clearDb();
    }

    @Test
    void enrollTest() {

        String username = "peach";
        String email = "baek0318@icloud.com";
        String password = "1234";

        UserAccountEnrollRequest request = new UserAccountEnrollRequest(email, username, password);

        given()
                .port(port)
                .accept("application/json")
                .contentType("application/json")
                .body(request)
        .when()
                .post("/api/users")
        .then()
                .statusCode(201)
                .body("id", equalTo(1));
    }
}
