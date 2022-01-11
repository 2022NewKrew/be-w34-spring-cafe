package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserCreationDTO;
import com.kakao.cafe.util.Encrypt;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.*;

class MemoryUserRepositoryTest {

    MemoryUserRepository repository = new MemoryUserRepository();
    static Encrypt encrypt;

    @BeforeAll
    static void initialize() {
        try {
            encrypt = new Encrypt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @DisplayName("개별 유저 저장 테스트")
    @Test
    void sava() {
        UserCreationDTO dto = createDummyDTO();

        User user = new User(encrypt, dto);
        repository.save(user);

        var result = repository.findById(user.getId()).get();
        assertThat(user).isEqualTo(result);
    }

    @DisplayName("전체 유저 저장 테스트")
    @Test
    void findAll() {
        UserCreationDTO dto1 = createDummyDTO();
        UserCreationDTO dto2 = createDummyDTO();
        User user1 = new User(encrypt, dto1);
        User user2 = new User(encrypt, dto2);

        repository.save(user1);
        repository.save(user2);

        assertThat(repository.findAll().size()).isEqualTo(2);
    }

    UserCreationDTO createDummyDTO() {
        UserCreationDTO dto = new UserCreationDTO();
        dto.setUserId("newUserId");
        dto.setPassword("newPassword");
        dto.setUserEmail("newUserEmail");

        return dto;
    }
}
