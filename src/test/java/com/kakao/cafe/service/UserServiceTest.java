package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Mock
    private UserRepository userRepository;

    @Test
    void save() {
        UserDto userDto = new UserDto();
        userDto.setUserId("trevi");
        userDto.setEmail("trevi.kim@abc.com");
        userDto.setName("김현석");
        userDto.setPassword("qweqwe");
        User user1 = userMapper.toEntity(userDto);
        given(userRepository.findById(1L)).willReturn(Optional.of(user1));

        userService.save(userDto);

        Assertions.assertThat(userService.findUser(1L)).usingRecursiveComparison().isEqualTo(user1);
    }
}