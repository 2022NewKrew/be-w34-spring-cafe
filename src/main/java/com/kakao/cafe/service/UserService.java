package com.kakao.cafe.service;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserListDto;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserTransformation userTransformation = new UserTransformation();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserListDto> getUserList() {
        List<UserDao> userDaoList = userRepository.selectAll();
        return userDaoList.stream().map(userTransformation::toUserListDto).collect(Collectors.toList());
    }

    public void createUser(User user) {
        userRepository.insert(user);
    }

    public UserProfileDto readUserProfileDto(String id) {
        return userTransformation.toUserProfileDto(userRepository.select(id));
    }

    public boolean login(String userId, String password, HttpSession session) {
        UserDao userDao = userRepository.select(userId);
        if (!userDao.getPassword().equals(password)) {
            return false;
        }
        UserProfileDto userProfileDto = new UserProfileDto(userDao.getId(), userDao.getName(), userDao.getEmail());
        session.setAttribute("sessionedUser", userProfileDto);
        return true;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
