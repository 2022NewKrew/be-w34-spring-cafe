package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserCreateRequest;
import com.kakao.cafe.dto.UserLoginRequest;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserCreateRequest userCreateRequest) {
        userRepository.save(UserMapper.INSTANCE.toEntity(userCreateRequest));
    }

    public User findUser(String userId) {
        return userRepository.findByUserId(userId).
                orElseThrow(() -> new IllegalArgumentException("해당 userId에 맞는 user가 존재하지 않습니다."));
    }
    public User findUser(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("해당 id에 맞는 user가 존재하지 않습니다."));
    }
    public List<User> findUserList() {
        return userRepository.findAll();
    }

    public void updateUserInfo(Long id, UserCreateRequest updateUserRequest) throws IllegalStateException {
        // updateUser 는 기존 user와 동일한 id를 갖기 때문에 채워주는 과정.
        User updateUser = UserMapper.INSTANCE.toEntity(updateUserRequest);
        updateUser.setId(id);

        User user = findUser(id);
        validatePasswordSame(user.getPassword(), updateUser.getPassword());
        userRepository.updateUser(id, updateUser);
    }

    private void validatePasswordSame(String userPassword, String updateUserPassword) {
        if(!userPassword.equals(updateUserPassword)){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }

    public User validateUserLogin(UserLoginRequest userLoginRequest) throws AuthenticationException {
        User user;
        try
        {
            user = findUser(userLoginRequest.getUserId());
        }
        catch (IllegalArgumentException e)
        {
            throw new AuthenticationException();
        }
        if(!user.getPassword().equals(userLoginRequest.getPassword()))
            throw new AuthenticationException();
        return user;
    }
    public void validateUserUpdate(User sessionUser, Long id) throws AuthenticationException {
        if(!sessionUser.getId().equals(id))
            throw new AuthenticationException();
    }
}
