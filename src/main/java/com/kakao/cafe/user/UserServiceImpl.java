package com.kakao.cafe.user;

import com.kakao.cafe.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * UserService의 구현체입니다.
 *
 * @author jm.hong
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long save(User user) throws SQLException {
        return userRepository.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean update(User user) {

        User origin = userRepository.findOne(user.getId());
        origin.setEmail(user.getEmail());
        origin.setName(user.getName());

        log.debug(origin.toString());

        return userRepository.update(origin);
    }

    @Override
    public User loginCheck(String userId, String password) throws BaseException{
        User user = userRepository.findOne(userId, password);

        if (user == null) {
            throw new BaseException("비밀번호가 틀렸습니다.");
        }

        return user;
    }
}
