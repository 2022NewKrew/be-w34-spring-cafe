package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.user.UserCreationDTO;
import com.kakao.cafe.dto.user.UserDTO;
import com.kakao.cafe.repository.user.MemoryUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final MemoryUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(MemoryUserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public long join(UserCreationDTO dto) throws IllegalArgumentException {
        User newUser = new User(dto);

        validateDuplicateNickname(newUser);
        validateDuplicateUserEmail(newUser);

        newUser.setPassword(encryptPassword(newUser.getPassword()));
        userRepository.save(newUser);

        return newUser.getId();
    }

    public long update(Long id, UserCreationDTO dto) throws Exception {
        var user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalAccessException("아이디가 존재하지 않습니다."));
        validatePassword(dto.getPrevPassword(), user.getPassword());

        user.setNickname(dto.getNickname());
        user.setPassword(encryptPassword(dto.getPassword()));
        userRepository.save(user);

        return user.getId();
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(m -> modelMapper.map(m, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO findById(long id) {
        return userRepository.findById(id)
                .map(m -> modelMapper.map(m, UserDTO.class))
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다"));
    }

    public UserDTO findByEmail(String userId) {
        return userRepository.findByEmail(userId)
                .map(m -> modelMapper.map(m, UserDTO.class))
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 이메일입니다."));
    }

    public void validatePassword(String inputRawPassword, String currentHashedPassword) {
        if (!passwordEncoder.matches(inputRawPassword, currentHashedPassword)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private void validateDuplicateNickname(User newUser) {
        userRepository.findByNickname(newUser.getNickname()).ifPresent(m -> {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        });
    }

    private void validateDuplicateUserEmail(User newUser) {
        userRepository.findByEmail(newUser.getEmail()).ifPresent(m -> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });
    }
}
