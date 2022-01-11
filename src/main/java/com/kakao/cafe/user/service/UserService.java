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
    }

    public GetSignUpResultResponseDTO getSignUpResultViewData(Long userId) {
        User user = userRepository.find(userId);
        return new GetSignUpResultResponseDTO(user.getEmail(), user.getNickName());
    }

    public FindAllUserResponseDTO getAllUserViewData(Long startIndex, Long endIndex) {
        ArrayList<User> userCollection = userRepository.findAll();
        if (startIndex < 0) {
            startIndex = 0L;
        }
        if (endIndex > userCollection.size()) {
            endIndex = userCollection.size() + 1L;
        }
        if (startIndex > userCollection.size() || startIndex >= endIndex) {
            return new FindAllUserResponseDTO(new ArrayList<User>());
        }
        Stream<User> stream = userCollection.stream();
        if (startIndex > 0) {
            stream = stream.skip(startIndex);
        }
        return new FindAllUserResponseDTO(stream.limit(endIndex - startIndex).collect(Collectors.toCollection(ArrayList::new)));
    }

    public FindAllUserResponseDTO getAllUserViewData(Long startIndex) {
        ArrayList<User> users = userRepository.findAll()
                .stream().skip(startIndex).collect(Collectors.toCollection(ArrayList::new));
        return new FindAllUserResponseDTO(users);
    }

    public GetProfileResponseDTO getUserProfile(Long userId) {
        User user = userRepository.find(userId);
        return new GetProfileResponseDTO(user.getNickName(), user.getEmail());
    }

    public String findUserNickNameById(Long userId) {
        return userRepository.find(userId).getNickName();
    }
}
