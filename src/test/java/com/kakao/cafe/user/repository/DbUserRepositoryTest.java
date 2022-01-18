package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class DbUserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void clear() {
        userRepository.clear();
    }

    @Test
    @DisplayName("유저 회원가입후 DB에서 조회시 정보가 같아야한다.")
    void save() {
        // given
        User user = new User(0, "cbsky1234", "1234", "skye", "skye@daum.net");
        userRepository.save(user);

        // when
        User getUser = userRepository.findOneByUserId("cbsky1234").get();

        // then
        assertThat(user.getUserId()).isEqualTo(getUser.getUserId());
    }

    @Test
    @DisplayName("회원가입 후 고유 ID로 조회시 정보가 같아야한다.")
    void findOneById() {
        // given
        User user = new User(0, "cbsky1234", "1234", "skye", "skye@daum.net");
        long ID = userRepository.save(user).getId();

        // when
        Optional<User> getUser = userRepository.findOneById(ID);

        // then
        assertThat(getUser.isEmpty()).isEqualTo(false);
        assertThat(user.getId()).isEqualTo(getUser.get().getId());
    }

    @Test
    @DisplayName("회원가입 후 userID로 조회시 정보가 같아야한다.")
    void findOneByUserId() {
        // given
        User user = new User(0, "cbsky1234", "1234", "skye", "skye@daum.net");
        userRepository.save(user);

        // when
        Optional<User> getUser = userRepository.findOneByUserId("cbsky1234");

        // then
        assertThat(getUser.isEmpty()).isEqualTo(false);
        assertThat(user.getUserId()).isEqualTo(getUser.get().getUserId());
    }
}