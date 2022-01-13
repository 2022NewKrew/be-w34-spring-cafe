package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class SpringUserServiceTest {
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private UserMapper userMapper;

    @Test
    @DisplayName("유저 저장 후 아이디 1로 불러왔을때 유저 정보가 일치해야 한다.")
    void saveAndFind() {
        UserDto userDto = new UserDto();
        userDto.setUserId("trevi");
        userDto.setEmail("trevi.kim@abc.com");
        userDto.setName("김현석");
        userDto.setPassword("qweqwe");
        User user = userMapper.toEntity(userDto);

        userService.save(userDto);
        User findUser = userService.findUser(1L);
        Assertions.assertThat(findUser.getName()).isEqualTo(userDto.getName());
        Assertions.assertThat(findUser.getUserId()).isEqualTo(userDto.getUserId());
        Assertions.assertThat(findUser.getEmail()).isEqualTo(userDto.getEmail());
        Assertions.assertThat(findUser.getPassword()).isEqualTo(userDto.getPassword());
    }
}
