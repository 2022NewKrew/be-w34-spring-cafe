package com.kakao.cafe.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by melodist
 * Date: 2022-02-03 003
 * Time: 오후 7:41
 */
@SpringBootTest
class JdbcUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void dataAccessExceptionTest(){
        // given
        Integer id = 10;

        // when, then
        assertThatThrownBy(() -> userRepository.findUserById(id))
                .isInstanceOf(DataAccessException.class);
    }

}
