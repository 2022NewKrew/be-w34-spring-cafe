package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.web.dto.UserCreateRequest;
import com.kakao.cafe.web.dto.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void create(UserCreateRequest requestDto) {
        userRepository.create(requestDto.toEntity());
    }

    @Transactional(readOnly = true)
    public List<UserCreateRequest> findAll() {
        return userRepository.findAll().stream()
                .map(UserCreateRequest::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserProfileResponse findById(String id) {
        return new UserProfileResponse(userRepository.findById(id));
    }
}
