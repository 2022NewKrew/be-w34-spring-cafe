package com.kakao.cafe.service;

import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.domain.UserAccountDTO;
import com.kakao.cafe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

public class UserAccountService {
    private final Repository<UserAccount, UserAccountDTO, String> userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAccountService(Repository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String join(UserAccountDTO userAccountDTO){
        userAccountDTO.setPassword(passwordEncoder.encode(userAccountDTO.getPassword()));

        validateDuplicateUserId(userAccountDTO);

        userRepository.save(userAccountDTO);
        return userAccountDTO.getUserId();
    }

    private void validateDuplicateUserId(UserAccountDTO userAccountDTO) {
        userRepository.findByUserId(userAccountDTO.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<UserAccount> findUserAccount(){
        return userRepository.findAll();
    }

    public Optional<UserAccount> findOne(String userId){
        return userRepository.findByUserId(userId);
    }

    public Optional<UserAccount> updateUserAccount(UserAccountDTO userAccountDTO, String curPassword){
        UserAccount savedUserAccount = userRepository.findByUserId(userAccountDTO.getUserId())
                .orElseThrow(() -> new InputMismatchException("아이디를 찾을 수 없습니다."));

        UserAccount newUserAccount = null;

        if(passwordEncoder.matches(curPassword, savedUserAccount.getPassword())){
            userAccountDTO.setPassword(passwordEncoder.encode(userAccountDTO.getPassword()));
            newUserAccount = userRepository.save(userAccountDTO);
        }

        return Optional.ofNullable(newUserAccount);
    }
}
