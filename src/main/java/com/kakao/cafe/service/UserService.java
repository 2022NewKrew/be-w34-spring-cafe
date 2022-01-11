package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.RepositoryInterface;
import com.kakao.cafe.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final RepositoryInterface<User> userRepository = new UserRepository();
    public void join(User user){
        validateDuplicateUser(user);

        userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        // 중복 ID 체크
        userRepository.findByName(user.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 ID입니다.");
                });
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long uid){
        return userRepository.findById(uid);
    }
}
