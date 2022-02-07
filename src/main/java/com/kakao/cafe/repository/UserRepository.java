package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.IllegalLoginInputException;
import com.kakao.cafe.repository.dao.UserDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Repository
public class UserRepository {

    private final UserDAO userDAO;

    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void create(User user) {
        userDAO.create(user);
    }

    public List<User> getUserList() {
        return userDAO.findAll();
    }

    public Optional<User> findByUserId(String userId) {
        return userDAO.findByUserId(userId);
    }

    public Optional<User> getSessionUser(String userId, String password) throws IllegalLoginInputException {
        checkIdValid(userId);
        Optional<User> user;
        user = this.findByUserId(userId);

        if (user.isEmpty())
            return Optional.empty();

        if (user.get().isPasswordMatching(password)) {
            return user;
        } else
            return Optional.empty();
    }

    private void checkIdValid(String userId) throws IllegalLoginInputException {
        String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|]*$";
        if (!Pattern.matches(pattern, userId))
            throw new IllegalLoginInputException("아이디에는 특수문자를 입력 하실 수 없습니다.");
    }
}
