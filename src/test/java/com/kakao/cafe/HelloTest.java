package com.kakao.cafe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class HelloTest {

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    void userTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
