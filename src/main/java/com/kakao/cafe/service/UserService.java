package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.RequestUserDto;
import com.kakao.cafe.dto.ResponseUserDto;
import com.kakao.cafe.repository.MemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    private final UserRepository userRepository = new MemoryUserRepository();


    public void join(RequestUserDto userDto) {
        Optional<User> result = userRepository.findByUserId(userDto.getUserId());
        result.ifPresent(u -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        User user = modelMapper.map(userDto, User.class);
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

        if(!userDto.getPrevPassword().equals(user.getPassword())){
            throw new IllegalStateException("패스워드가 일치하지 않습니다.");
        }

        User newUser = modelMapper.map(userDto, User.class);
        user.setPassword(userDto.getPassword());
        userRepository.updateById(id, user);
    }
}
