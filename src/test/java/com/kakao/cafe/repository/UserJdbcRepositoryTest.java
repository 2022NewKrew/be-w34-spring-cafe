package com.kakao.cafe.repository;

import com.kakao.cafe.controller.dto.UserJoinDto;
import com.kakao.cafe.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserJdbcRepositoryTest {

    @Autowired
    private UserJdbcRepository userJdbcRepository;

    @Test
    @DisplayName("저장하고 찾아오기")
    void save() {
        //given
        String userId = "idid";

        UserJoinDto dto = new UserJoinDto();
        dto.setPassword("1234");
        dto.setEmail("kakao@com");
        dto.setUserId(userId);
        dto.setName("lsh");

        //when
        User user = User.from(dto);
        userJdbcRepository.save(user);

        // then
        User byId = userJdbcRepository.findById(userId).get();
        Assertions.assertThat(byId).isEqualTo(user);
    }

    @Test
    @DisplayName("리스트 가져오기")
    void getList() {
        //given
        String userId = "idid";

        UserJoinDto dto = new UserJoinDto();
        dto.setPassword("1234");
        dto.setEmail("kakao@com");
        dto.setUserId(userId);
        dto.setName("lsh");

        String userId2 = "idid2";

        UserJoinDto dto2 = new UserJoinDto();
        dto2.setPassword("1234");
        dto2.setEmail("kakao@com");
        dto2.setUserId(userId2);
        dto2.setName("lsh");

        //when
        User user = User.from(dto);
        userJdbcRepository.save(user);
        User user2 = User.from(dto2);
        userJdbcRepository.save(user2);

        // then
        List<User> all = userJdbcRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
    }
}
