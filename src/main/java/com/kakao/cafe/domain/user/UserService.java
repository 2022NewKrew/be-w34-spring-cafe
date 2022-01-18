package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.repository.UserRepository;
import com.kakao.cafe.global.error.exception.NoSessionException;
import com.kakao.cafe.global.error.exception.NoSuchUserException;
import com.kakao.cafe.global.error.exception.PasswordNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(User user){
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    // 중복회원 검사 (userId:아이디 를 기준으로)
    private void validateDuplicateUser(User user) {
        userRepository.findByUserId(user.getUserId())
                .ifPresent(u -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findByUserId(String userId){
        return userRepository.findByUserId(userId).orElseThrow(() -> {throw new NoSuchUserException();});
    }

    public User updateUser(User user) {
        User findUser = findByUserId(user.getUserId());
        checkPassword(user, findUser);
        user.setId(findUser.getId());
        return userRepository.update(user);
    }

    private void checkPassword(User user, User findUser) {
        if (!user.getPassword().equals(findUser.getPassword()))
            throw new PasswordNotMatchException();
    }
}
