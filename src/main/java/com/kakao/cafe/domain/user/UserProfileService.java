package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.dao.UserFindDao;
import com.kakao.cafe.domain.user.dao.UserRepository;
import com.kakao.cafe.domain.user.dto.UserProfileDto;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserRepository userRepository;

    private final UserFindDao userFindDao;

    public UserProfileService(UserRepository userRepository, UserFindDao userFindDao) {
        this.userRepository = userRepository;
        this.userFindDao = userFindDao;
    }

    public UserResponseDto show(UserProfileDto dto) {
        User user = userFindDao.findByUserId(dto.getUserId());
        return UserResponseDto.of(user);
    }

    public void update(UserProfileDto dto) {
        User user = userFindDao.findByUserId(dto.getUserId());
        user.validatePassword(dto.getPassword());
        user.setNickname(dto.getNickname());
        user.setEmail(dto.getEmail());
        userRepository.update(user);
    }
}
