package com.kakao.cafe.controller.listview;

import com.kakao.cafe.constant.PageSize;
import com.kakao.cafe.dto.user.SimpleUserInfo;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserListViewControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserListViewController userListViewController;

    private ModelAndPageView mapv;

    @BeforeEach
    void setUp() {
        mapv = mock(ModelAndPageView.class);
    }

    @Test
    @DisplayName("유저 리스트 화면 반환 -> 정상, check mav")
    void userList_checkMav() {
        //Given
        int numOfUser = 214;
        given(userService.countAll()).willReturn(numOfUser);

        int pageNum = 1;
        List<SimpleUserInfo> userInfos = Collections.emptyList();
        given(userService.getListOfSimpleUserInfo(pageNum, PageSize.USER_LIST_SIZE)).willReturn(userInfos);

        //When
        userListViewController.userListView(pageNum, mapv);

        //Then
        then(mapv).should(times(1)).addObject("numOfElement", numOfUser);
        then(mapv).should(times(1)).addObject("elementInfos", userInfos);
        then(mapv).should(times(1)).setPageNumbers(pageNum, numOfUser / PageSize.USER_LIST_SIZE + 1);
        then(mapv).should(times(1)).setViewName("userList");
    }
}