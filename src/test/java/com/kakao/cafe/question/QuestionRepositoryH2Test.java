package com.kakao.cafe.question;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionRepositoryH2Test {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void deleteOne() throws Exception {
        //given
        Long id = 3L;

        //when
        boolean rs = questionRepository.deleteOne(id);

        //then
        Assertions.assertThat(rs).isEqualTo(true);
    }

}
