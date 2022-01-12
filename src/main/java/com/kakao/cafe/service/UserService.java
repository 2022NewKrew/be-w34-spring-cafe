package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.ShowUserDto;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(CreateUserDto createUserDto) {
        User user = new User(createUserDto);
        userRepository.save(user);
    }

    public List<ShowUserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(ShowUserDto::new)
                .collect(Collectors.toList());
    }

    public ShowUserDto findById(String userId) {
        return new ShowUserDto(userRepository.findById(userId));
    }
}
