package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.IllegalUserInputException;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.web.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User userFromUserDTO(UserDTO userDTO) {
        return User.builder()
                .userId(userDTO.getUserId())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .registerDate(userDTO.getRegisterDate())
                .build();
    }

    public UserDTO userDTOFromUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .password(user.getPassword())
                .email(user.getEmail())
                .registerDate(user.getRegisterDate())
                .build();
    }

    public void createUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findByUserId(userDTO.getUserId());
        if (user.isEmpty())
            userRepository.create(userFromUserDTO(userDTO));
        else
            throw new IllegalUserInputException("이미 존재하는 아이디입니다.");
    }

    public UserDTO getUserByUserId(String userId) throws IllegalUserInputException {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userDTOFromUser(user);
        }
        throw new IllegalUserInputException("해당 아이디의 유저가 없습니다.");
    }

    public List<UserDTO> getUserDTOList() {
        return userRepository.getUserList().stream().map(this::userDTOFromUser).collect(Collectors.toList());
    }

    public int getUserListSize() {
        return userRepository.getUserList().size();
    }

    private boolean isPasswordMatching(UserDTO userDTO, String checkPassword) {
        User user = User.builder().password(userDTO.getPassword()).build();
        return user.isPasswordMatching(checkPassword);
    }

    public Optional<UserDTO> getSessionUserDTO(String userId, String password) {

        UserDTO userDTO;
        try {
            userDTO = this.getUserByUserId(userId);
        } catch (IllegalUserInputException e) {
            return Optional.empty();
        }

        if (this.isPasswordMatching(userDTO, password)) {
            return Optional.of(UserDTO.newInstanceNonePasswordInfo(userDTO));
        } else
            return Optional.empty();

    }
}
