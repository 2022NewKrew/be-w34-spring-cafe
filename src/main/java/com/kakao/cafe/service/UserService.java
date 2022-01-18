package com.kakao.cafe.service;

import com.kakao.cafe.dao.user.UserDao;
import com.kakao.cafe.model.user.Email;
import com.kakao.cafe.model.user.Name;
import com.kakao.cafe.model.user.Password;
import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserId;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updateUser(String userId, String password, String name, String email) {
        checkPassword(findUser(new UserId(userId)), new Password(password));

        userDao.update(new UserId(userId), new Name(name), new Email(email));
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User findUserByUserId(String userId) {
        return findUser(new UserId(userId));
    }

    public void createUser(String userId, String password, String name, String email) {
        userDao.addUser(new UserId(userId), new Password(password), new Name(name),
                new Email(email));
    }

    public void login(String userId, String password) {
        User user = userDao.findUserById(new UserId(userId))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        if (user.isPassword(new Password(password))) {
            return;
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    private User findUser(UserId userId) {
        return userDao
                .findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 없습니다!"));
    }

    private void checkPassword(User user, Password inputPassword) {
        if (!user.isPassword(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
