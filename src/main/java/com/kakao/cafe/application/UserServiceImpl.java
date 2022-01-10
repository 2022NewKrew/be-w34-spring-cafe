package com.kakao.cafe.application;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.interfaces.user.dto.UserMapper;
import com.kakao.cafe.interfaces.user.dto.request.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void join(UserDto userDto) {
        User user = UserMapper.convertUserDtoToEntity(userDto);
        userRepository.save(user);
    }

}
