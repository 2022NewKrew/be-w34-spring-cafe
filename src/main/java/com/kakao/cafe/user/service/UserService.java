package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.repository.UserCreateRequestDTO;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.repository.UserUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
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
        Optional<User> op = userRepository.find(userId);
        User user = op.orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));
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
        Optional<User> op = userRepository.find(userId);
        User user = op.orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return new UserProfileResponseDTO(user.getNickName(), user.getEmail(), user.getStringId());
    }

    public UserProfileResponseDTO getUserProfile(String stringId) {
        return getUserProfile(getUserDBId(stringId));
    }

    private Long getUserDBId(String stringId) {
        Optional<User> user = userRepository.find(stringId);
        return user.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다.")).getId();
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
        Optional<User> op = userRepository.find(userId);
        User user = op.orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        if (password.equals(user.getPassword())){
            return true;
        }
        return false;
    }
}
