package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserForm;
import com.kakao.cafe.dto.UserList;
import com.kakao.cafe.dto.UserListElement;
import com.kakao.cafe.dto.UserProfile;
import com.kakao.cafe.repository.UserMemoryRepository;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.utility.ErrorCode;
import com.kakao.cafe.utility.UserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserMemoryRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void join(UserForm userForm) {
        validateDuplicateUser(userForm.getUserId());
        User user = new User(userForm);
        userRepository.save(user);
    }

    private void validateDuplicateUser(String userId) {
        if(userRepository.findByUserId(userId).isPresent())
            throw new UserException(ErrorCode.DUPLICATE_USER_ID);
    }

    public UserList findUsers() {
        List<UserListElement> userList = userRepository.findAll()
                .stream()
                .map(user -> new UserListElement(user.getUserId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
        return new UserList(userList);
    }

    public User findUserByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if(user.isEmpty()) {
            throw new UserException(ErrorCode.USER_NOT_FOUND);
        }
        return user.get();
    }

    public UserProfile findUserProfile(String userId) {
        User user = findUserByUserId(userId);
        return new UserProfile(user.getName(), user.getEmail());
    }
}
