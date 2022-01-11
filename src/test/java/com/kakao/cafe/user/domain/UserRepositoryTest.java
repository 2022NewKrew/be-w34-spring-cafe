package com.kakao.cafe.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.user.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {

    private UserRepository userRepository;
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        userRepository = new MemoryUserRepository();
        user1 = User.builder()
            .email("jin.jang@kakaocorp.com")
            .password("password")
            .username("jin")
            .build();
        user2 = User.builder()
            .email("tom@kakaocorp.com")
            .password("password")
            .username("tom")
            .build();
    }

    @DisplayName("유저를 저장하면 기본키가 자동으로 증가한다.")
    @Test
    void save_PK_AutoInc() {
        User saved1 = userRepository.save(user1);
        assertThat(saved1.getId()).isEqualTo(1L);

        User saved2 = userRepository.save(user2);
        assertThat(saved2.getId()).isEqualTo(2L);
    }

    @DisplayName("유저네임을 통해 유저를 가져온다.")
    @Test
    void findByUsername_Saved_User() {
        User saved = userRepository.save(user1);
        assertThat(saved.getId()).isEqualTo(1L);
        assertThat(userRepository.findByUsername(user1.getUsername())
            .orElseThrow(UserNotFoundException::new)).isEqualTo(saved);
    }

    @DisplayName("유저네임에 해당하는 유저가 없으면 Optional.empty()를 반환한다.")
    @Test
    void findByUsername_NoSaved_Exception() {
        assertThat(userRepository.findByUsername(user1.getUsername())).isEmpty();
    }

    @DisplayName("저장된 모든 유저를 가져온다.")
    @Test
    void findAll_Saved_Users() {
        userRepository.save(user1);
        userRepository.save(user2);
        assertThat(userRepository.findAll()).hasSize(2);
    }
}
