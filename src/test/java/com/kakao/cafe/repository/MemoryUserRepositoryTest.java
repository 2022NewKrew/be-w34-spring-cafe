//package com.kakao.cafe.repository;
//
//import com.kakao.cafe.controller.UserController;
//import com.kakao.cafe.domain.User;
//import com.kakao.cafe.dto.user.UserCreationDTO;
//import com.kakao.cafe.repository.user.MemoryUserRepository;
//import com.kakao.cafe.util.Encrypt;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.security.NoSuchAlgorithmException;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//class MemoryUserRepositoryTest {
//
//    MemoryUserRepository repository = new MemoryUserRepository();
//    static Encrypt encrypt;
//
//    @Autowired
//    UserController userController;
//
//    @BeforeAll
//    static void setup() {
//        try {
//            encrypt = new Encrypt();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @AfterEach
//    void teardown() {
//        repository.clearStore();
//    }
//
//    @DisplayName("개별 유저 저장 테스트")
//    @Test
//    void sava() {
//        UserCreationDTO dto = createDummyDTO();
//
//        User user = new User(dto);
//        repository.save(user);
//
//        var result = repository.findById(user.getId()).get();
//        assertThat(user.getEmail()).isEqualTo(result.getEmail());
//    }
//
//    @DisplayName("전체 유저 저장 테스트")
//    @Test
//    void findAll() {
//        UserCreationDTO dto1 = createDummyDTO();
//        UserCreationDTO dto2 = createDummyDTO();
//        User user1 = new User(dto1);
//        User user2 = new User(dto2);
//
//        repository.save(user1);
//        repository.save(user2);
//
//        var userSize = repository.findAll().size();
//        assertThat(userSize).isEqualTo(2);
//    }
//
//    UserCreationDTO createDummyDTO() {
//        UserCreationDTO dto = new UserCreationDTO();
//        dto.setNickname("newUserId");
//        dto.setPassword("newPassword");
//        dto.setEmail("newUserEmail");
//
//        return dto;
//    }
//}
