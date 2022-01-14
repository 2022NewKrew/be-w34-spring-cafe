package com.kakao.cafe.user;

public class UserMapper {
    public static User toUser(UserCreationForm userCreationForm) {
        return new User(null, userCreationForm.getEmail(), userCreationForm.getUsername(),
                        userCreationForm.getPassword(), UserStatus.ACTIVE.name(), userCreationForm.getDisplayName(),
                        null, null);
    }

    public static UserView toUserView(User user) {
        return new UserView(user.getEmail(), user.getUsername(), user.getDisplayName());
    }
}
