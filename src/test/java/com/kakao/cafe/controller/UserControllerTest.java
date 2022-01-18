package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.ProfileDto;
import com.kakao.cafe.dto.user.SimpleUserInfo;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.testutil.user.UserDtoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ModelAndView mav;

    @BeforeEach
    void setUp() {
        mav = mock(ModelAndView.class);
    }

    @Test
    @DisplayName("유저 프로필 화면 반환 -> 정상, check mav")
    void userProfile() {
        //Given
        Long userId = Long.valueOf(25);
        ProfileDto profileDto = UserDtoUtil.createProfileDto(userId);
        given(userService.findProfileById(userId)).willReturn(profileDto);

        //When
        userController.userProfile(userId, mav);

        //Then
        then(mav).should(times(1)).addObject("profile", profileDto);
        then(mav).should(times(1)).setViewName("userProfile");
    }

    @Test
    @DisplayName("회원 가입 성공 화면 반환 -> 정상, check mav")
    void joinSuccessView() {
        //Given
        Long userId = Long.valueOf(12);
        SimpleUserInfo simpleUserInfo = UserDtoUtil.createSimpleUserInfo();
        given(userService.findSimpleUserInfoById(userId)).willReturn(simpleUserInfo);

        //When
        userController.joinSuccessView(userId, mav);

        //Then
        then(mav).should(times(1)).addObject("simpleUserInfo", simpleUserInfo);
        then(mav).should(times(1)).setViewName("join_success");
    }
}