package com.kakao.cafe.application.user;

import com.kakao.cafe.application.dto.command.UserAccountEnrollCommand;
import com.kakao.cafe.application.exception.IdNotFoundException;
import com.kakao.cafe.domain.user.UserAccount;
import com.kakao.cafe.domain.user.UserAccountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountService(@Qualifier("jdbc-account-db") UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount enroll(UserAccountEnrollCommand command) {
        duplicateCheck(command.getEmail());
        return userAccountRepository.save(command.toEntity());
    }

    private void duplicateCheck(String email) {
        if (userAccountRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("유효하지 않는 값입니다");
        }
    }

    public UserAccount getUserInfoByEmail(String email) {
        return userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 값입니다"));
    }

    public List<UserAccount> getAllUser() {
        return userAccountRepository.findAll();
    }

    public UserAccount getUserInfo(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("유효하지 않는 값입니다"));
    }
}
