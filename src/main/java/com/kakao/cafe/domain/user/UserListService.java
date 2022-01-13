package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.dao.UserFindDao;
import com.kakao.cafe.domain.user.dto.UserProfileDto;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListService {

    private final UserFindDao userFindDao;

    public UserListService(UserFindDao userFindDao) {
        this.userFindDao = userFindDao;
    }

    public UserResponseDto retrieveUser(UserProfileDto requestDto) {
        User user = userFindDao.findByUserId(requestDto.getUserId());
        return UserResponseDto.of(user);
    }

    public List<UserResponseDto> retrieveAllUsers() {
        List<User> users = userFindDao.findAll();
        return UserResponseDto.of(users);
    }
}
