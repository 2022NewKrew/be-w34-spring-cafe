package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.UserSaveForm;
import com.kakao.cafe.controller.dto.UserJoinForm;
import com.kakao.cafe.controller.dto.UserListDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.AlreadyExistId;
import com.kakao.cafe.exception.NoSuchUser;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public String join(UserJoinForm dtoUser) {
        if(isAlreadyExist(dtoUser)) {
            throw new AlreadyExistId("이미 존재하는 아이디");
        }

        User user = User.from(dtoUser);
        userRepository.save(user);
        return user.getUserId();
    }

    public List<UserListDto> getUserList() {
        List<User> userList = userRepository.findAll();
        return IntStream.range(0, userList.size()).mapToObj(i -> UserListDto.from(userList.get(i), i+1)).collect(Collectors.toList());
    }

    public User findUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchUser("그런 사용자는 없습니다."));
    }

    public void updateUser(String userId, UserSaveForm dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchUser("그런 사용자는 없습니다."));
        if(!user.chcekPassword(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        user.updateEmailAndName(dto.getEmail(), dto.getName());
        userRepository.update(user);
    }

    private boolean isAlreadyExist(UserJoinForm dtoUser) {
        return userRepository.findById(dtoUser.getUserId()).isPresent();
    }
}
