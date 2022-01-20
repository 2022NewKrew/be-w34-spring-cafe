package com.kakao.cafe.application.user;

import com.kakao.cafe.application.user.exception.NonExistsUserIdException;
import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.User;

import java.util.List;
import java.util.Optional;

public class FindUserService {
    private final FindUserPort findUserPort;

    public FindUserService(FindUserPort findUserPort) {
        this.findUserPort = findUserPort;
    }

    public User findByUserId(String userId) throws NonExistsUserIdException {
        return findUserPort.findByUserId(userId)
                .orElseThrow(() -> new NonExistsUserIdException());
    }

    public List<User> findAllUser() {
        return findUserPort.findAll();
    }

    public boolean checkPassWordMatch(String userId, String password) {
        Optional<User> optionalUser = findUserPort.findByUserIdAndPassword(userId, password);
        return optionalUser.isPresent();
    }
}
