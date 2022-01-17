package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.RequestUserDto;
import com.kakao.cafe.dto.ResponseUserDto;

import com.kakao.cafe.repository.user.H2UserRepository;

import com.kakao.cafe.repository.user.MemoryUserRepository;
import com.kakao.cafe.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private ModelMapper modelMapper;

    private final UserRepository userRepository;

    public UserService(@Qualifier("h2UserRepository") H2UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void join(RequestUserDto userDto) {
        Optional<User> result = userRepository.findByUserId(userDto.getUserId());
        result.ifPresent(u -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        User user = modelMapper.map(userDto, User.class);

        user.setJoinedAt(new Date());
        userRepository.save(user);
    }

    public List<ResponseUserDto> findUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, ResponseUserDto.class))
                .collect(Collectors.toList());
    }

    public ResponseUserDto findOne(long id) {
        User result = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당하는 회원이 존재하지 않습니다."));

        return modelMapper.map(result, ResponseUserDto.class);
    }

    public long getCountOfUser() {
        return userRepository.countRecords();
    }

    public void updateUser(long id, RequestUserDto userDto) {
        User user = userRepository.findByUserId(userDto.getUserId()).orElseThrow(() -> new IllegalStateException("해당하는 회원이 존재하지 않습니다."));

        if (!userDto.getPrevPassword().equals(user.getPassword())) {
            throw new IllegalStateException("패스워드가 일치하지 않습니다.");
        }

        User newUser = modelMapper.map(userDto, User.class);
        user.setPassword(userDto.getPassword());
        userRepository.updateById(id, user);
    }

    public boolean login(String userId, String password) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalStateException("해당하는 회원이 존재하지 않습니다."));
        if (!user.getPassword().trim().equals(password)) {
            throw new IllegalStateException("패스워드가 일치하지 않습니다.");
        }
        return true;
    }
}
