package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.repository.user.UserRepository;
import com.kakao.cafe.dto.RequestUserDto;
import com.kakao.cafe.dto.ResponseUserDto;
import com.kakao.cafe.dto.SessionUser;
import com.kakao.cafe.exception.UserAlreadyExistException;
import com.kakao.cafe.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    /*
     * 회원가입
     */
    public void join(RequestUserDto userDto) {
        Optional<User> result = userRepository.findByUserId(userDto.getUserId());
        result.ifPresent(u -> {
            throw new UserAlreadyExistException();
        });
        User user = modelMapper.map(userDto, User.class);
        String hashedPw = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        user.setHashedPw(hashedPw);
        user.setJoinedAt(new Date());
        userRepository.save(user);
    }

    /*
     * 전체 유저 조회
     */
    public List<ResponseUserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, ResponseUserDto.class))
                .collect(Collectors.toList());
    }

    /*
     * id로 유저 조회
     */
    public ResponseUserDto getUserById(long id) {
        User result = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return modelMapper.map(result, ResponseUserDto.class);
    }

    /*
     * userId로 유저 조회
     */
    public ResponseUserDto getUserByUserId(String userId) {
        User result = userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException());
        return modelMapper.map(result, ResponseUserDto.class);
    }

    /*
     * 전체 유저 숫자 조회
     */
    public long getCountOfUser() {
        return userRepository.countRecords();
    }

    /*
     * id로 유저 정보 수정
     */
    public void updateUser(long id, RequestUserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

        validatePassword(user, userDto.getPassword());

        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        String hashedPw = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        user.setHashedPw(hashedPw);
        userRepository.save(user);
    }

    /*
     * 로그인
     */
    public SessionUser login(String userId, String password) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException());
        validatePassword(user, password);
        return modelMapper.map(user, SessionUser.class);
    }

    public void validatePassword(User user, String password) {
        if (!BCrypt.checkpw(password, user.getHashedPw())) {
            throw new IllegalStateException("패스워드가 일치하지 않습니다.");
        }
    }

}
