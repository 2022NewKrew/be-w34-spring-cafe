package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserLoginRequest;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void save(UserDto userDto) {
        userRepository.save(UserMapper.INSTANCE.toEntity(userDto));
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

    public void updateUserInfo(Long id, UserDto updateUserDto) {
        // updateUser 는 기존 user와 동일한 id를 갖기 때문에 채워주는 과정.
        User updateUser = UserMapper.INSTANCE.toEntity(updateUserDto);
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
}
