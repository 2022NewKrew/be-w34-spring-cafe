package com.kakao.cafe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class HelloTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/fruit 페이지에서 과일 정보를 표시해 주어야 한다")
    void fruitTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fruit")
                .param("name", "apple")
                .param("sugar", "99"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
