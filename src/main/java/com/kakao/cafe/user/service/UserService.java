package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.service.dto.AllUserProfileServiceResponse;
import com.kakao.cafe.user.service.dto.UserProfileServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserServiceDTOMapper userServiceResponseMapper;

    @PostConstruct
    private void init() {
        createUser("aiden.jang", "aiden@kakaocorp.com", "에이든", "1234");
        createUser("javajigi", "javajigi@slipp.net", "자바지기", "test");
        createUser("sanjigi", "sanjigi@slipp.net", "산지기", "test");
        log.info("Add test user data: 에이든, 자바지기, 산지기");
    }

    public Long createUser(String stringId, String email, String name, String password) {
        User user = User.builder()
                .stringId(stringId)
                .email(email)
                .name(name)
                .password(password).build();
        return userRepository.persist(user);
    }

    public UserProfileServiceResponse getSignUpResultViewData(Long userId) {
        Optional<User> op = userRepository.find(userId);
        User user = op.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return userServiceResponseMapper.convertToUserProfileServiceResponse(user);
    }

    public AllUserProfileServiceResponse getAllUserViewData(Long startIndex, Long endIndex) {
        ArrayList<User> userCollection = userRepository.findAll();
        if (startIndex < 0) {
            startIndex = 0L;
        }
        if (endIndex > userCollection.size()) {
            endIndex = userCollection.size() + 1L;
        }
        if (startIndex > userCollection.size() || startIndex >= endIndex) {
            return new AllUserProfileServiceResponse(new ArrayList<User>());
        }
        Stream<User> stream = userCollection.stream();
        if (startIndex > 0) {
            stream = stream.skip(startIndex);
        }
        ArrayList<User> users = stream.limit(endIndex - startIndex).collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(users);
        return new AllUserProfileServiceResponse(users);
    }

    public AllUserProfileServiceResponse getAllUserViewData(Long startIndex) {
        ArrayList<User> users = userRepository.findAll()
                .stream().skip(startIndex).collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(users);
        return new AllUserProfileServiceResponse(users);
    }

    public UserProfileServiceResponse getUserProfile(String stringId) {
        Optional<User> op = userRepository.find(stringId);
        User user = op.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return userServiceResponseMapper.convertToUserProfileServiceResponse(user);
    }

    private Long getUserDBId(String stringId) {
        Optional<User> user = userRepository.find(stringId);
        return user.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다.")).getId();
    }


    public void updateUserInfo(String stringId, String oldPassword, String newPassword, String name, String email) {
        // 검증
        if (!validateUserPassWord(stringId, oldPassword)) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        // 업데이터
        User user = User.builder()
                .stringId(stringId)
                .password(newPassword)
                .name(name)
                .email(email)
                .build();

        userRepository.updateUserInfo(user);
    }


    public boolean validateUserPassWord(String stringId, String password) {
        Optional<User> op = userRepository.find(stringId);
        User user = op.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        if (password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }


}
