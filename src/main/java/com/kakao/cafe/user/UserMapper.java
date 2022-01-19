package com.kakao.cafe.user;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserStatus;
import com.kakao.cafe.user.dto.LoggedInUser;
import com.kakao.cafe.user.dto.UserCreationForm;
import com.kakao.cafe.user.dto.UserLoginForm;
import com.kakao.cafe.user.dto.UserView;

public class UserMapper {
    public static User toUser(UserCreationForm userCreationForm) {
        return new User(null, userCreationForm.getEmail(), userCreationForm.getUsername(),
                        userCreationForm.getPassword(), UserStatus.ACTIVE.name(), userCreationForm.getDisplayName(),
                        null, null);
    }

    public static UserView toUserView(User user) {
        return new UserView(user.getEmail(), user.getUsername(), user.getDisplayName());
    }

    public static User toUser(UserLoginForm userLoginForm) {
        return new User(null, null, userLoginForm.getUsername(), userLoginForm.getPassword(), null, null, null, null);
    }

    public static LoggedInUser toLoggedInUser(User user) {
        return new LoggedInUser(user.getUsername());
    }
}
