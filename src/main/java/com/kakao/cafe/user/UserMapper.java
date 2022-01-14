package com.kakao.cafe.user;

public class UserMapper {
    public static User toUser(UserFormCreationDTO userFormCreationDTO) {
        return new User(null, userFormCreationDTO.getUsername(), userFormCreationDTO.getPassword(),
                        userFormCreationDTO.getEmail());
    }

    public static UserViewDTO toUserViewDTO(User user) {
        return new UserViewDTO(user.getUsername(), user.getEmail());
    }
}
