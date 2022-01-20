package com.kakao.cafe.impl.service;

import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.exception.NoChangeException;
import com.kakao.cafe.exception.NoModifyPermissionException;
import com.kakao.cafe.exception.WrongPasswordException;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
    private static final String NO_UPDATE_MESSAGE = "수정할 내용이 없습니다.";
    private static final String WRONG_PASSWORD_MESSAGE = "잘못된 비밀번호입니다.";
    private static final String ACCESS_DENIED_MESSAGE = "다른유저의 정보에는 접근할 수 없습니다.";
    @Resource(name = "userRepository")
    UserRepository userRepository;

    @Override
    public long insertUser(UserDTO user) {
        return userRepository.insertUser(user);
    }

    @Override
    public List<UserDTO> getUserList() {
        return userRepository.getAllUser();
    }

    @Override
    public UserDTO getUserById(long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void updateUser(long id, UserDTO user, String password) {
        if (id != user.getId()) {
            throw new NoModifyPermissionException(ACCESS_DENIED_MESSAGE);
        }
        if (!Objects.equals(password, user.getPassword())) {
            throw new WrongPasswordException(WRONG_PASSWORD_MESSAGE);
        }
        if (userRepository.updateUser(user) <= 0) {
            throw new NoChangeException(NO_UPDATE_MESSAGE);
        }
    }

    @Override
    public UserDTO getUserByLoginData(LoginDTO login) {
        return userRepository.getUserByLoginData(login);
    }
}
