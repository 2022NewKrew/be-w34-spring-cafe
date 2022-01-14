package kakao.bew34springcafe;

import kakao.bew34springcafe.web.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeW34SpringCafeApplicationTest {
    Logger logger = LoggerFactory.getLogger(BeW34SpringCafeApplicationTest.class);

    @Autowired
    private UserController userController;

//    @Test
//    @DisplayName("/fruit 페이지에서 과일 정보를 표시해 주어야 한다")
//    void fruitTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/fruit")
//                        .param("name", "apple")
//                        .param("sugar", "99"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

}
