//package com.kakao.cafe.controller;
//
//import com.kakao.cafe.service.UserService;
//import com.kakao.cafe.vo.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.then;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//
//@ExtendWith(MockitoExtension.class)
//class UserControllerTest {
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    private ModelAndView mav;
//
//    @BeforeEach
//    void setUp() {
//        mav = mock(ModelAndView.class);
//    }
//
//    @Test
//    @DisplayName("유저 리스트 반환 -> 정상")
//    void userList() {
//        // given
////        given(userService.getUsers()).willReturn(List<User>);
//
//    }
//
//    @Test
//    void createUser() {
//        // given
//        User user = new User("ABC", "1234", "test@naver.com");
////        given(userService.join(user)).willReturn(void);
//    }
//
//    @Test
//    void user() {
//    }
//}