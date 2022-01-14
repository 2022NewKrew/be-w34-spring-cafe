package com.kakao.cafe.domain;

import com.kakao.cafe.domain.article.Text;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TextTest {

    @Test
    @DisplayName("줄바꿈 형식 변환 테스트")
    void enterTest() {
        // given
        String inputText = "hello!\r\nworld!";

        // when
        Text text = new Text(inputText);

        // then
        assertThat(text.toString().contains("<br>")).isEqualTo(true);
    }

    @Test
    @DisplayName("XSS 방지 변환 테스트")
    void XssTest() {
        // given
        String scriptText = "<script>something</script>";

        // when
        Text text = new Text(scriptText);

        // then
        assertThat(text.toString().contains("<script>")).isEqualTo(false);
    }
}
