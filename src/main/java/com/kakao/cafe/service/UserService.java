package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public long join(UserDTO userDTO) {
        User user = User.fromDTO(userDTO);
        return userRepository.insert(user);
    }

    public List<User> findAll() {
        return userRepository.selectAll();
    }

    public Optional<User> findByKey(Long key) {
        return userRepository.selectByKey(key);
    }

    public Optional<UserDTO> findByKeyDTO(Long key) {
        return userRepository.selectByKey(key).map(User::getDTO);
    }

    public Optional<User> login(UserDTO userDTO) {
        Optional<User> user = userRepository.selectById(userDTO.getId());
        if (user.isEmpty()) return Optional.empty();
        if (!user.get().getPw().equals(userDTO.getPw())) return Optional.empty();
        return user;
    }

    public void updateByKey(Long key, UserDTO userDTO) {
        User user = User.fromDTO(userDTO);
        userRepository.update(key, user);
    }
}
