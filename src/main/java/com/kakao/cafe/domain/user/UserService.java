package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.dao.UserDao;
import com.kakao.cafe.domain.user.dto.UserUpdateDto;
import com.kakao.cafe.domain.user.dto.UserTableRowDto;
import com.kakao.cafe.domain.user.dto.UserSignUpDto;
import com.kakao.cafe.domain.user.exception.UserAlreadyExistException;
import com.kakao.cafe.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public Long signUp(UserSignUpDto dto) {
        userDao.findByEmail(dto.getEmail())
                .ifPresent(obj -> {
                    throw new UserAlreadyExistException(obj.getEmail());
                });
        return userDao.save(dto.toEntity());
    }

    public void updateProfile(UserUpdateDto dto) {
        User user = userDao.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(dto.getEmail()));
        user.validatePassword(dto.getPassword());
        user.updateInfo(dto.toEntity());
        userDao.update(user);
    }

    public UserTableRowDto retrieveById(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserTableRowDto.of(user);
    }

    public List<UserTableRowDto> retrieveAll() {
        List<User> users = userDao.findAll();
        return UserTableRowDto.of(users);
    }
}
