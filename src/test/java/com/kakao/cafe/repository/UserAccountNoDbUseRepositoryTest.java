package com.kakao.cafe.repository;

import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.domain.UserAccountDTO;
import com.kakao.cafe.repository.user.UserAccountNoDbUseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserAccountNoDbUseRepositoryTest {
    UserAccountNoDbUseRepository repository = new UserAccountNoDbUseRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    @DisplayName("DB에 저장")
    void save() {
        UserAccountDTO userAccountDTO = new UserAccountDTO("aa", "aa", "aa", "aa@com");

        UserAccount savedUserAccount = repository.save(userAccountDTO);
        UserAccount result = repository.findById(userAccountDTO.getUserId()).get();
        assertThat(savedUserAccount).isEqualTo(result);
    }

    @Test
    @DisplayName("userId로 데이터 조회")
    void findById() {
        UserAccountDTO userAccountDTO = new UserAccountDTO("aa", "aa", "aa", "aa@com");

        UserAccount savedUserAccount = repository.save(userAccountDTO);

        UserAccount result = repository.findById("aa").get();

        assertThat(result).isEqualTo(savedUserAccount);
    }

    @Test
    @DisplayName("전체 데이터 조회")
    void findAll() {
        UserAccountDTO userAccountDTO = new UserAccountDTO("aa", "aa", "aa", "aa@com");
        repository.save(userAccountDTO);

        UserAccountDTO userAccountDTO2 = new UserAccountDTO("bb", "bb", "bb", "bb@com");
        repository.save(userAccountDTO2);

        List<UserAccount> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
