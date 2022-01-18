package com.kakao.cafe.service;

import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.domain.UserAccountDTO;
import com.kakao.cafe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

public class UserAccountService implements Service<UserAccount, UserAccountDTO, String>{
    private final Repository<UserAccount, UserAccountDTO, String> userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAccountService(Repository<UserAccount, UserAccountDTO, String> userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String join(UserAccountDTO userAccountDTO) throws IllegalStateException{
        userAccountDTO.setPassword(passwordEncoder.encode(userAccountDTO.getPassword()));

        validateDuplicateUserId(userAccountDTO);

        userRepository.save(userAccountDTO);
        return userAccountDTO.getUserId();
    }

    private void validateDuplicateUserId(UserAccountDTO userAccountDTO) throws IllegalStateException{
        userRepository.findById(userAccountDTO.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("중복된 userId로 가입 요청을 하였습니다.");
                });
    }

    @Override
    public List<UserAccount> findAll(){
        return userRepository.findAll();
    }

    @Override
    public Optional<UserAccount> findOne(String userId){
        return userRepository.findById(userId);
    }

    @Override
    public void delete(String id) {

    }

    public Optional<UserAccount> updateUserAccount(UserAccountDTO userAccountDTO, String curPassword) throws InputMismatchException{
        UserAccount savedUserAccount = userRepository.findById(userAccountDTO.getUserId())
                .orElseThrow(() -> new InputMismatchException(userAccountDTO.getUserId() + "로 가입된 유저 계정을 찾을 수 없습니다."));

        UserAccount newUserAccount = null;

        if(passwordEncoder.matches(curPassword, savedUserAccount.getPassword())){
            userAccountDTO.setPassword(passwordEncoder.encode(userAccountDTO.getPassword()));
            newUserAccount = userRepository.save(userAccountDTO);
        }

        return Optional.ofNullable(newUserAccount);
    }

    public boolean isPasswordEqual(UserAccount userAccount, String userInputPassword){
        return passwordEncoder.matches(userInputPassword, userAccount.getPassword());
    }

    public boolean isVaildUserAccess(String userId, Object value){
        if(value != null){
            UserAccount userAccount = (UserAccount) value;

            if(userAccount.getUserId().equals(userId))
                return true;
        }

        return false;
    }
}
