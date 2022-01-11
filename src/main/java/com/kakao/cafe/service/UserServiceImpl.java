package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.vo.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public void addUser(UserDto userDto){
        validateDuplicateUser(userDto);
        userRepository.save(userDto);
    }

    private void validateDuplicateUser(UserDto userDto) {
        userRepository.findByName(userDto.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

}
