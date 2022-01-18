package com.kakao.cafe.controller.listview;

import com.kakao.cafe.constant.PageSize;
import com.kakao.cafe.dto.user.SimpleUserInfo;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserListViewController extends ListViewController<SimpleUserInfo> {

    private final UserService userService;

    @GetMapping("/users")
    public ModelAndView userListView(@RequestParam(defaultValue = "1") Integer pageNum, ModelAndPageView mapv) {
        return listView(pageNum, mapv);
    }

    @Override
    protected int getNumOfElement() {
        return userService.countAll();
    }

    @Override
    protected int getPageSize() {
        return PageSize.USER_LIST_SIZE;
    }

    @Override
    protected List<SimpleUserInfo> getSimpleElementInfos(Integer pageNum, Integer pageSize) {
        return userService.getListOfSimpleUserInfo(pageNum, pageSize);
    }

    @Override
    protected String getListViewName() {
        return "userList";
    }
}
