package com.kakao.cafe.user;

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
    public Long save(User user) {
        Long id = null;

        try {
            id = userRepository.save(user);
        } catch (SQLException e) {
            log.error("USER TABLE SAVE 실패 SQLState : {}",e.getSQLState());
        }

        return id;
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
    public User loginCheck(String userId, String password) {

        return userRepository.findOne(userId, password);
    }
}
