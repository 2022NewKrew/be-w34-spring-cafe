package com.kakao.cafe.presentation;

import com.kakao.cafe.presentation.dto.request.QuestionPostWriteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionPostApiControllerTest {

    @LocalServerPort
    int port;

    @Test
    void writeTest() {
        String title = "spring question";
        String content = "what is this?";
        Long userId = 0L;

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
                .body("id", equalTo(0));
    }
}
