package com.kakao.cafe.service.user;

import com.kakao.cafe.dao.user.UserDao;
import com.kakao.cafe.model.user.Password;
import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserFactory;
import com.kakao.cafe.model.user.UserId;
import com.kakao.cafe.service.Constant;
import com.kakao.cafe.service.user.dto.UserElementDto;
import com.kakao.cafe.service.user.dto.UserInformationDto;
import com.kakao.cafe.service.user.dto.UserLoginDto;
import com.kakao.cafe.service.user.dto.UserRegisterDto;
import com.kakao.cafe.service.user.dto.UserUpdateDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updateUser(UserUpdateDto userUpdateDto) {
        User user = UserFactory.getUser(userUpdateDto);
        checkPassword(user.getUserId(), user.getPassword());
        userDao.update(user);
    }

    public List<UserElementDto> getUsers() {
        List<User> users = userDao.getUsers();
        return IntStream
                .range(0, users.size())
                .mapToObj(i -> new UserElementDto(users.get(i), i + 1))
                .collect(Collectors.toList());
    }

    public UserInformationDto findUserByUserId(String userId) {
        return new UserInformationDto(findUser(new UserId(userId)));
    }

    public void createUser(UserRegisterDto userRegisterDto) {
        User user = UserFactory.getUser(userRegisterDto);

        checkDuplicated(user.getUserId());

        userDao.addUser(user);
    }

    public void login(UserLoginDto userLoginDto) {
        checkPassword(new UserId(userLoginDto.getUserId()),
                new Password(userLoginDto.getPassword()));
    }

    private User findUser(UserId userId) {
        return userDao
                .findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException(Constant.USER_IS_NOT_EXIST));
    }

    private void checkPassword(UserId userId, Password Password) {
        User user = findUser(userId);
        if (!user.isPassword(Password)) {
            throw new IllegalArgumentException(Constant.NOT_MATCH_PASSWORD);
        }
    }

    private void checkDuplicated(UserId userId) {
        if (userDao.findUserById(userId).isPresent()) {
            throw new IllegalArgumentException(Constant.ALREADY_EXIST_USER);
        }
    }
}
