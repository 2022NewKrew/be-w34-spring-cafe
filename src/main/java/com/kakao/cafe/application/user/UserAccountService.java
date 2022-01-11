package com.kakao.cafe.application.user;

import com.kakao.cafe.application.dto.command.UserAccountEnrollCommand;
import com.kakao.cafe.application.exception.IdNotFoundException;
import com.kakao.cafe.domain.user.UserAccount;
import com.kakao.cafe.domain.user.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount enroll(UserAccountEnrollCommand command) {
        duplicateCheck(command.getEmail());
        return userAccountRepository.save(command.toEntity());
    }

    private void duplicateCheck(String email) {
        if(userAccountRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("유효하지 않는 값입니다");
        }
    }

    public List<UserAccount> getAllUser() {
        return userAccountRepository.findAll();
    }

    public UserAccount getUserInfo(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("유효하지 않는 값입니다"));
    }
}
