package com.kakao.cafe;

import com.kakao.cafe.config.ControllerTestConfig;
import com.kakao.cafe.post.application.*;
import com.kakao.cafe.post.presentation.PostController;
import com.kakao.cafe.user.application.JoinService;
import com.kakao.cafe.user.application.LoginService;
import com.kakao.cafe.user.application.SearchUserService;
import com.kakao.cafe.user.application.UpdateUserInfoService;
import com.kakao.cafe.user.presentation.UserController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({
        UserController.class,
        PostController.class
})
@Import({ControllerTestConfig.class})
public abstract class ControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected SearchUserService searchUserService;

    @MockBean
    protected LoginService loginService;

    @MockBean
    protected JoinService joinService;

    @MockBean
    protected UpdateUserInfoService updateUserInfoService;

    @MockBean
    protected SearchPostService postInfoService;

    @MockBean
    protected WritePostService writePostService;

    @MockBean
    protected UpdatePostService updatePostService;

    @MockBean
    protected DeletePostService deletePostService;

    @MockBean
    protected AddCommentService commentService;

    @MockBean
    protected DeleteCommentService deleteCommentService;

    @Autowired
    protected ModelMapper modelMapper;
}
