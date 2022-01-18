package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.dto.RequestUserDto;
import com.kakao.cafe.domain.dto.ResponseUserDto;

import com.kakao.cafe.repository.user.H2UserRepository;

import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.domain.dto.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {
    @Autowired
    private ModelMapper modelMapper;

    private final UserRepository userRepository;

    public UserService(@Qualifier("h2UserRepository") H2UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void join(RequestUserDto userDto) {
        Optional<User> result = userRepository.findByUserId(userDto.getUserId());
        result.ifPresent(u -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        User user = modelMapper.map(userDto, User.class);
        String hashedPw = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        user.setHashedPw(hashedPw);
        user.setJoinedAt(new Date());
        userRepository.save(user);
    }

    public List<ResponseUserDto> findUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, ResponseUserDto.class))
                .collect(Collectors.toList());
    }

    public ResponseUserDto findOne(long id) {
        User result = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당하는 회원이 존재하지 않습니다."));

        return modelMapper.map(result, ResponseUserDto.class);
    }

    public ResponseUserDto findOne(String userId) {
        User result = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalStateException("해당하는 회원이 존재하지 않습니다."));

        return modelMapper.map(result, ResponseUserDto.class);
    }

    public long getCountOfUser() {
        return userRepository.countRecords();
    }

    public void updateUser(long id, RequestUserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당하는 회원이 존재하지 않습니다."));

        validatePassword(user, userDto.getPassword());

        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        String hashedPw = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        user.setHashedPw(hashedPw);
        userRepository.save(user);
    }

    public SessionUser login(String userId, String password) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalStateException("해당하는 회원이 존재하지 않습니다."));
        validatePassword(user, password);

        SessionUser result = modelMapper.map(user, SessionUser.class);
        log.info("userVo : {}", result);
        log.info("user : {}", user);
        return result;
    }

    public void validatePassword(User user, String password) {
        if (!BCrypt.checkpw(password, user.getHashedPw())) {
            throw new IllegalStateException("패스워드가 일치하지 않습니다.");
        }
    }

    // TO DO: 없애는 방법 고민
    public boolean isOwnerOfId(long id, String ownerUserId) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당하는 회원이 존재하지 않습니다."));
        if (user.getUserId().equals(ownerUserId)) {
            return true;
        }
        return false;
    }

}
