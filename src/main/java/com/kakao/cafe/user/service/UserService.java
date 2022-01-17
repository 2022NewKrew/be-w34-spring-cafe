package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.repository.UserCreateRequestDTO;
import com.kakao.cafe.user.repository.UserRepository;
import com.kakao.cafe.user.repository.UserUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @PostConstruct
    private void init() {
        createUser("aiden.jang", "aiden@kakaocorp.com", "에이든", "1234");
        createUser("javajigi", "javajigi@slipp.net", "자바지기", "test");
        createUser("sanjigi", "sanjigi@slipp.net", "산지기", "test");
        log.info("Add test user data: 에이든, 자바지기, 산지기");
    }
    public Long createUser(String stringId, String email, String nickName, String passWord) {
        return userRepository.persist(new UserCreateRequestDTO(stringId, email, nickName, passWord, LocalDateTime.now()));
    }

    public GetSignUpResultResponseDTO getSignUpResultViewData(Long userId) {
        User user = userRepository.find(userId);
        return new GetSignUpResultResponseDTO(user.getStringId(), user.getEmail(), user.getNickName());
    }

    public AllUsersResponseDTO getAllUserViewData(Long startIndex, Long endIndex) {
        ArrayList<User> userCollection = userRepository.findAll();
        if (startIndex < 0) {
            startIndex = 0L;
        }
        if (endIndex > userCollection.size()) {
            endIndex = userCollection.size() + 1L;
        }
        if (startIndex > userCollection.size() || startIndex >= endIndex) {
            return new AllUsersResponseDTO(new ArrayList<User>());
        }
        Stream<User> stream = userCollection.stream();
        if (startIndex > 0) {
            stream = stream.skip(startIndex);
        }
        ArrayList<User> users = stream.limit(endIndex - startIndex).collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(users);
        return new AllUsersResponseDTO(users);
    }

    public AllUsersResponseDTO getAllUserViewData(Long startIndex) {
        ArrayList<User> users = userRepository.findAll()
                .stream().skip(startIndex).collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(users);
        return new AllUsersResponseDTO(users);
    }

    public UserProfileResponseDTO getUserProfile(Long userId) {
        User user = userRepository.find(userId);
        return new UserProfileResponseDTO(user.getNickName(), user.getEmail(), user.getStringId());
    }

    public String findUserNickNameById(Long userId) {
        return userRepository.find(userId).getNickName();
    }

    public UserProfileResponseDTO getUserProfile(String stringId) {
        return getUserProfile(getUserDBId(stringId));
    }

    private Long getUserDBId(String stringId) {
        return userRepository.findDBIdById(stringId);
    }

    public void updateUserInfo(Long userId, String oldPassword, String newPassword, String name, String email) {
        // 검증
        if (!validateUserPassWord(userId, oldPassword)) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        // 업데이터
        userRepository.updateUserInfo(new UserUpdateRequestDTO(userId, newPassword, name, email));
    }

    public void updateUserInfo(String userStringId, String oldPassword, String newPassword, String name, String email) {
        updateUserInfo(getUserDBId(userStringId), oldPassword, newPassword, name, email);
    }


    public boolean validateUserPassWord(Long userId, String password) {
        if (password.equals(userRepository.findPasswordByDBId(userId))) {
            return true;
        }
        return false;
    }
}
