package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.repository.CreateUserDTO;
import com.kakao.cafe.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService {

    UserRepository userRepository = UserRepository.getRepository();

    public void createUser(String email, String nickName, String passWord, LocalDateTime signUpDate) {
        userRepository.persist(new CreateUserDTO(email, nickName, passWord, signUpDate));
//        userRepository.persist(new User(email, nickName, passWord, signUpDate));
    }

    public SignUpResultViewDTO getSignUpResultViewData(Long userId) {
        User user = userRepository.find(userId);
        return new SignUpResultViewDTO(user.getEmail(), user.getNickName());
    }

    public AllUserViewDTO getAllUserViewData(Long startIndex, Long endIndex) {
        ArrayList<User> userCollection = userRepository.findAll();
        if (startIndex < 0) {
            startIndex = 0L;
        }
        if (endIndex > userCollection.size()) {
            endIndex = userCollection.size() + 1L;
        }
        if (startIndex > userCollection.size() || startIndex >= endIndex) {
            return new AllUserViewDTO(new ArrayList<User>());
        }
        Stream<User> stream = userCollection.stream();
        if (startIndex > 0) {
            stream = stream.skip(startIndex);
        }
        return new AllUserViewDTO(stream.limit(endIndex - startIndex).collect(Collectors.toCollection(ArrayList::new)));
    }

    public AllUserViewDTO getAllUserViewData(Long startIndex) {
        ArrayList<User> users = userRepository.findAll()
                .stream().skip(startIndex).collect(Collectors.toCollection(ArrayList::new));
        return new AllUserViewDTO(users);
    }

    public ProfileViewDTO getUserProfile(Long userId) {
        User user = userRepository.find(userId);
        return new ProfileViewDTO(user.getNickName(), user.getEmail());
    }
}
