package com.kakao.cafe.Service;

import com.kakao.cafe.Domain.User;
import com.kakao.cafe.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String join(User user){
        validateNickname(user);
        userRepository.saveUser(user);
        return user.getNickName();
    }

    private void validateNickname(User user) {
        userRepository.findByNickName(user.getNickName())
                .ifPresent(u->{
                    throw new IllegalStateException("Nickname already exists.");
                });
    }

    public List<User> findUsers(){
        return userRepository.findAllUsers();
    }

    public Optional<User> findOne(String userNickname){
        return userRepository.findByNickName(userNickname);
    }

    public Optional<User> findOne(Long id){
        return userRepository.findByUserId(id);
    }

}
