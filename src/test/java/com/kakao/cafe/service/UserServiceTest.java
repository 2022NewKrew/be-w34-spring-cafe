package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.dto.user.UserInfoDto;
import com.kakao.cafe.dto.user.UserProfileDto;
import com.kakao.cafe.exception.InputDataException;
import com.kakao.cafe.vo.UserVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserDto userDto;
    private UserVo userVo;

    @InjectMocks
    UserService userService;

    @Mock
    UserDao userDao;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void init() {
        userDto = new UserDto();
        userDto.setName("김대환");
        userDto.setUserId("david.dh");
        userDto.setPassword("1234");
        userDto.setEmail("david.dh@kakaocorp.com");
        userVo = new UserVo(userDto.getUserId(),userDto.getPassword(),userDto.getName(),userDto.getEmail());
    }

    @Test
    @DisplayName("회원 정보 가져오기")
    void getUser() throws InputDataException {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserId("david.dh");
        userInfoDto.setName("김대환");
        userInfoDto.setEmail("david.dh@kakaocorp.com");
        doNothing().when(userDao).save(any());
        userService.addUser(userDto);
        when(userDao.findByUserId(any())).thenReturn(userVo);
        when(modelMapper.map(any(),any())).thenReturn(userInfoDto);
        UserInfoDto user = userService.getUser(userDto.getUserId());

        assertThat(userInfoDto.getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    @DisplayName("회원 아이디 중복")
    void addDuplicatedUser() {
        when(userDao.findByUserId(any())).thenReturn(userVo);
        assertThatThrownBy(() -> userService.addUser(userDto)).isInstanceOf(InputDataException.class).hasMessage("이미 존재하는 아이디입니다.");
    }


}
