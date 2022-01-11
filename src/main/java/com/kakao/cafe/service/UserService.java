package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserFormDTO;
import com.kakao.cafe.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    public long join(UserFormDTO userFormDTO) {
        User user = modelMapper.map(userFormDTO, User.class);
        return userMapper.insert(user);
    }

    public List<User> findAll() {
        return userMapper.selectAll();
    }

    public User findByKey(long key) {
        return userMapper.selectByKey(key);
    }
}
