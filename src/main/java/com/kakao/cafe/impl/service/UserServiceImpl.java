package com.kakao.cafe.impl.service;

import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.SessionUserDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.exception.NoChangeException;
import com.kakao.cafe.exception.NoModifyPermissionException;
import com.kakao.cafe.exception.WrongPasswordException;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Slf4j
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
    private static final String NO_UPDATE_MESSAGE = "수정할 내용이 없습니다.";
    private static final String FAIL_LOGIN_MESSAGE = "이메일 혹은 비밀번호가 틀렸습니다.";
    private static final String ACCESS_DENIED_MESSAGE = "다른유저의 정보에는 접근할 수 없습니다.";
    private static final String WRONG_PASSWORD_MESSAGE = "잘못된 비밀번호입니다.";
    private static final String DUPLICATED_USER_INFO_MESSAGE = "중복된 이메일 혹은 닉네임입니다.";
    @Resource(name = "userRepository")
    UserRepository userRepository;

    @Override
    public void insertUser(UserDTO user) {
        if (userRepository.insertUser(user) < 1) {
            throw new NoModifyPermissionException(DUPLICATED_USER_INFO_MESSAGE);
        }
        log.info("create User -> UserId : {}, Email : {}", user.getUserId(), user.getEmail());
    }

    @Override
    public List<UserDTO> getUserList() {
        return userRepository.getAllUser();
    }

    @Override
    public void getUserById(long id, Model model) {
        UserDTO user = userRepository.getUserById(id);
        model.addAttribute("user", user);
        log.info("get User(Profile) -> ID : {}, UserId : {}", user.getId(), user.getUserId());
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
        log.info("update User -> Id : {}, Email : {}", id, user.getEmail());
    }

    @Override
    public void loginByLoginData(LoginDTO login) {
        HttpSession session = Util.getSession();
        UserDTO user = userRepository.getUserByLoginData(login);
        if (user == null) {
            log.info("login fail -> email : {}", login.getEmail());
            throw new WrongPasswordException(FAIL_LOGIN_MESSAGE);
        }
        log.info("login success -> ID : {}, UserId : {}", user.getId(), user.getUserId());
        session.setAttribute("sessionUser", new SessionUserDTO(user));
    }

    @Override
    public void logOut() {
        HttpSession session = Util.getSession();
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        if (user != null) {
            log.info("logout -> ID : {}, UserId : {}", user.getId(), user.getUserId());
            session.invalidate();
        }
    }
}
