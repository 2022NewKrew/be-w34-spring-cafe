package com.kakao.cafe.presentation;

import com.kakao.cafe.post.adapter.in.web.dto.request.QuestionPostWriteRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionPostApiControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @AfterEach
    void tearDown() {
        jdbcTemplate.update("truncate table question_post");
        jdbcTemplate.update("alter table question_post alter column question_post_id restart with 1");
    }

    @Test
    void writeTest() {
        String title = "spring question";
        String content = "what is this?";
        Long userId = 1L;

        QuestionPostWriteRequest request = new QuestionPostWriteRequest(title, content, userId);

        given()
                .port(port)
                .accept("application/json")
                .contentType("application/json")
                .body(request)
        .when()
                .post("/api/posts")
        .then()
                .statusCode(201)
                .body("id", equalTo(1));
    }
}
