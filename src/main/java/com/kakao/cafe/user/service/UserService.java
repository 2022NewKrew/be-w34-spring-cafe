package com.kakao.cafe.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.service.dto.AllUserProfileServiceResponse;
import com.kakao.cafe.user.service.dto.UserProfileServiceResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @PostConstruct
    private void init() {
        createUser("aiden.jang", "aiden@kakaocorp.com", "에이든", "1234");
        createUser("javajigi", "javajigi@slipp.net", "자바지기", "1234");
        createUser("sanjigi", "sanjigi@slipp.net", "산지기", "1234");
        createUser("wcts", "wcts@kakao.com", "내이름", "1234");
        log.info("Add basic user data: 에이든, 자바지기, 산지기");
    }


    public Long createUser(String stringId, String email, String name, String password) {
        User user = makeUser(stringId, password, name, email);
        return userRepository.persist(user);
    }

    // 페이징 구현 시 리펙토링 필요
    public AllUserProfileServiceResponse getAllUserViewData(Long startIndex) {
        ArrayList<User> users = userRepository.findAll().stream()
                                              .skip(startIndex)
                                              .collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(users);
        return new AllUserProfileServiceResponse(users);
    }

    public UserProfileServiceResponse getUserProfile(String stringId) {
        Optional<User> op = userRepository.find(stringId);
        User user = op.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return UserServiceDTOMapper.convertToUserProfileServiceResponse(user);
    }

    public void updateUserInfo(String stringId, String oldPassword, String newPassword, String name, String email) {
        // 검증
        validateUser(stringId, oldPassword);
        User user = makeUser(stringId, newPassword, name, email);
        userRepository.updateUserInfo(user);
    }

    public Long validateUser(String stringId, String password) {
        Optional<User> op = userRepository.find(stringId);
        User user = op.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        if (!password.equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        return user.getId();
    }


    private User makeUser(String stringId, String password, String name, String email) {
        return User.builder()
                   .stringId(stringId)
                   .password(password)
                   .name(name)
                   .email(email)
                   .build();
    }

    // 세션 구현 후 활용 예정 메서드
//    public UserProfileServiceResponse getSignUpResultViewData(Long userId) {
//        Optional<User> op = userRepository.find(userId);
//        User user = op.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
//        return UserServiceDTOMapper.convertToUserProfileServiceResponse(user);
//    }

    // DB 페이진 구현 후 활용 예정 메서드
//    public AllUserProfileServiceResponse getAllUserViewData(Long startIndex, Long endIndex) {
//        ArrayList<User> userCollection = userRepository.findAll();
//        if (startIndex < 0) {
//            startIndex = 0L;
//        }
//        if (endIndex > userCollection.size()) {
//            endIndex = userCollection.size() + 1L;
//        }
//        if (startIndex > userCollection.size() || startIndex >= endIndex) {
//            return new AllUserProfileServiceResponse(new ArrayList<User>());
//        }
//        Stream<User> stream = userCollection.stream();
//        if (startIndex > 0) {
//            stream = stream.skip(startIndex);
//        }
//        ArrayList<User> users = stream.limit(endIndex - startIndex).collect(Collectors.toCollection(ArrayList::new));
//        Collections.reverse(users);
//        return new AllUserProfileServiceResponse(users);
//    }

}
