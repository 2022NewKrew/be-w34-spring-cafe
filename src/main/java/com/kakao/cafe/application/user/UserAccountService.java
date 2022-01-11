package com.kakao.cafe.application.user;

import com.kakao.cafe.application.dto.UserAccountEnrollCommand;
import com.kakao.cafe.domain.user.UserAccount;
import com.kakao.cafe.domain.user.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount enroll(UserAccountEnrollCommand command) {
        duplicateCheck(command.getEmail());
        UserAccount userAccount = UserAccount.builder()
                .password(command.getPassword())
                .username(command.getUsername())
                .email(command.getEmail())
                .createdAt(LocalDate.now())
                .build();

        return userAccountRepository.save(userAccount).orElseThrow(IllegalAccessError::new);
    }

    private void duplicateCheck(String email) {
        if(userAccountRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException();
        }
    }

    public List<UserAccount> getAllUser() {
        return userAccountRepository.findAll();
    }

    public UserAccount getUserInfo(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
