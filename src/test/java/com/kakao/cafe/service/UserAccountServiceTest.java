package com.kakao.cafe.service;

import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.domain.UserAccountDTO;
import com.kakao.cafe.repository.user.UserAccountNoDbUseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.InputMismatchException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserAccountServiceTest {
    UserAccountService userAccountService;
    UserAccountNoDbUseRepository userAccountNoDbUseRepository;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void beforeEach(){
        passwordEncoder = new BCryptPasswordEncoder();
        userAccountNoDbUseRepository = new UserAccountNoDbUseRepository();
        userAccountService = new UserAccountService(userAccountNoDbUseRepository, passwordEncoder);
    }

    @AfterEach
    public void afterEach(){
        userAccountNoDbUseRepository.clearStore();
    }

    @Test
    @DisplayName("회원가입")
    void join() {
        //given
        UserAccountDTO userAccountDTO = new UserAccountDTO("aa", "aa", "aa", "aa@com");

        //when
        String userId = userAccountService.join(userAccountDTO);

        //then
        UserAccount findUserAccount = userAccountService.findOne(userId).get();
        assertThat(userAccountDTO.getUserId()).isEqualTo(findUserAccount.getUserId());
    }

    @Test
    @DisplayName("중복 회원 예외")
    void dupleicateJoin(){
        //givin
        UserAccountDTO userAccountDTO = new UserAccountDTO("aa", "aa", "aa", "aa@com");

        UserAccountDTO userAccountDTO2 = new UserAccountDTO("aa", "bb", "bb", "bb@com");

        //when
        userAccountService.join(userAccountDTO);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userAccountService.join(userAccountDTO2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then

    }

    @Test
    @DisplayName("전체 조회")
    void findAll() {
        //givin
        UserAccountDTO userAccountDTO = new UserAccountDTO("aa", "aa", "aa", "aa@com");
        userAccountService.join(userAccountDTO);

        UserAccountDTO userAccountDTO2 = new UserAccountDTO("bb", "bb", "bb", "bb@com");
        userAccountService.join(userAccountDTO2);

        //when
        List<UserAccount> userAccounts = userAccountService.findAll();

        //then
        assertThat(userAccounts.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("하나 조회")
    void findOne() {
        //givin
        UserAccountDTO userAccountDTO = new UserAccountDTO("aa", "aa", "aa", "aa@com");
        userAccountService.join(userAccountDTO);

        //when
        UserAccount userAccount = userAccountService.findOne(userAccountDTO.getUserId()).get();

        //then
        assertThat(userAccountDTO.getUserId()).isEqualTo(userAccount.getUserId());
    }

    @Test
    @DisplayName("회원 정보 업데이트")
    void updateUserAccount() {
        //givin
        UserAccountDTO userAccountDTO = new UserAccountDTO("aa", "aa", "aa", "aa@com");
        userAccountService.join(userAccountDTO);

        UserAccountDTO userAccountDTO2 = new UserAccountDTO("aa", "bb", "bb", "bb@com");
        String curPassword = "bb";

        //when
        UserAccount userAccount = null;
        try {
            userAccount = userAccountService.updateUserAccount(userAccountDTO2, curPassword)
                    .orElseThrow(() -> new IllegalAccessError("비밀 번호가 일치하지 않습니다"));

            assertThat(userAccount).isEqualTo(userAccountService.findOne(userAccountDTO.getUserId()).get());
        } catch (IllegalAccessError e) {
            assertThat(e.getMessage()).isEqualTo("비밀 번호가 일치하지 않습니다");
        } catch (InputMismatchException e){
            assertThat(e.getMessage()).isEqualTo("아이디를 찾을 수 없습니다.");
        }

        //then

    }
}
