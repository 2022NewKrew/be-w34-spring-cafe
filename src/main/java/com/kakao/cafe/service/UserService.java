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

    public void updateUser(Long userId, User newUser) {
        User user = findOne(userId);
        user.setEmail(newUser.getEmail());
        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new IllegalStateException("not valid userId");
    }
}
