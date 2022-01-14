package com.kakao.cafe.domain;

import com.kakao.cafe.domain.article.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TitleTest {

    @Test
    @DisplayName("XSS 방지 변환 테스트")
    void XssTest() {
        // given
        String scriptText = "<script>something</script>";

        // when
        Title title = new Title(scriptText);

        // then
        System.out.println(title);
        assertThat(title.toString()).isEqualTo("scriptsomethingscript");
    }
}
