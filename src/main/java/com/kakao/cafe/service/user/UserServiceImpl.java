package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.entity.UserEntity;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDto register(UserDto userDto) {
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        UserEntity savedEntity = userRepository.save(userEntity);

        return userMapper.toUserDto(savedEntity);
    }

    @Override
    public void login(UserDto userDto) {

    }

    @Override
    public List<UserDto> allUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        return userEntities.stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
