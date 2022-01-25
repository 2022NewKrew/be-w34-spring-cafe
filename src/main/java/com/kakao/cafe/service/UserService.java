package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.IllegalLoginInputException;
import com.kakao.cafe.exception.IllegalUserInputException;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
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

    public UserResponseDTO userResponseDTOFromUser(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .password(user.getPassword())
                .userId(user.getUserId())
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

    public UserResponseDTO getUserByUserId(String userId) throws IllegalUserInputException {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userResponseDTOFromUser(user);
        }
        throw new IllegalUserInputException("해당 아이디의 유저가 없습니다.");
    }

    public List<UserResponseDTO> getUserDTOList() {
        return userRepository.getUserList().stream().map(this::userResponseDTOFromUser).collect(Collectors.toList());
    }

    public int getUserListSize() {
        return userRepository.getUserList().size();
    }

    private boolean isPasswordMatching(UserResponseDTO userResponseDTO, String checkPassword) {
        User user = User.builder().password(userResponseDTO.getPassword()).build();
        return user.isPasswordMatching(checkPassword);
    }
    private void checkIdValid(String userId){
        String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|]*$";
        if(!Pattern.matches(pattern, userId))
            throw new IllegalLoginInputException("아이디에는 특수문자를 입력 하실 수 없습니다.");
    }

    public Optional<UserResponseDTO> getSessionUserDTO(String userId, String password) {
        checkIdValid(userId);
        UserResponseDTO userResponseDTO;
        try {
            userResponseDTO = this.getUserByUserId(userId);
        } catch (IllegalUserInputException e) {
            return Optional.empty();
        }

        if (this.isPasswordMatching(userResponseDTO, password)) {
            return Optional.of(UserResponseDTO.nonePasswordInstance(userResponseDTO));
        } else
            return Optional.empty();
    }
}
