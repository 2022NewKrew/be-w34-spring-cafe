package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserProfileDto;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface UserService {
    void signup(UserDto u) throws SQLException;
    UserProfileDto findById(String s) throws NoSuchElementException;
    UserProfileDto findByName(String s) throws NoSuchElementException;
    List<UserProfileDto> getUserList();
    void updateUserProfile(UserProfileDto u, String s) throws NoSuchElementException, IllegalArgumentException;
    UserProfileDto checkPassword(String userId, String password);
    boolean checkSessionUser(String userId, HttpSession session);
    String getUserIdFromSession(HttpSession session);
}
