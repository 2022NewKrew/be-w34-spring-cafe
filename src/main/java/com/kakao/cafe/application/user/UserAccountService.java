package com.kakao.cafe.application.user;

import com.kakao.cafe.application.dto.UserAccountEnrollCommand;
import com.kakao.cafe.domain.user.UserAccount;
import com.kakao.cafe.domain.user.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount enroll(UserAccountEnrollCommand command) {
        UserAccount userAccount = UserAccount.builder()
                .password(command.getPassword())
                .username(command.getUsername())
                .email(command.getEmail())
                .createdAt(LocalDate.now())
                .build();

        return userAccountRepository.save(userAccount).orElseThrow(IllegalAccessError::new);
    }

    public List<UserAccount> getAllUser() {
        return userAccountRepository.findAll();
    }

    public UserAccount getUserInfo(Long id) {
        return userAccountRepository.find(id).orElseThrow(IllegalArgumentException::new);
    }
}
