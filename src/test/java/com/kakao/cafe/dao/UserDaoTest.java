package com.kakao.cafe.dao;

import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.vo.UserVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Autowired
    ModelMapper modelMapper;

    @Test
    @DisplayName("회원 조회")
    void findByUserId() {
        UserDto userDto = new UserDto();
        userDto.setUserId("david.dh");
        userDto.setPassword("1234");
        userDto.setName("김대환");
        userDto.setEmail("david.dh@kakaocorp.com");

        userDao.save(modelMapper.map(userDto, UserVo.class));

        UserVo matchedUser = userDao.findByUserId(userDto.getUserId());
        assertThat(userDto.toString()).isEqualTo(modelMapper.map(matchedUser,UserDto.class).toString());

    }
}
