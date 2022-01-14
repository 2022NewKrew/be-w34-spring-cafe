package com.kakao.cafe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long registerUser(UserFormCreationDTO userFormCreationDTO) {
        User user = UserMapper.toUser(userFormCreationDTO);
        validateDuplicateUserName(user);

        return userRepository.add(user);
    }

    private void validateDuplicateUserName(User user) {
        if (userRepository.get(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already in use: " + user.getUsername());
        }
    }

    public List<UserViewDTO> getAllUserViewDTOUsers() {
        return userRepository.getAll().stream().map(UserMapper::toUserViewDTO).collect(Collectors.toList());
    }

    public UserViewDTO getUserViewDTOByUsername(String username) {
        return UserMapper.toUserViewDTO(userRepository.get(username).orElseThrow(
                () -> new NoSuchElementException("Username not found: " + username)));
    }
}
