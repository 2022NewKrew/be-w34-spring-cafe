package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserResponse;
import com.kakao.cafe.dto.user.UserUpdateReqDto;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.dto.user.UserRequest;
import com.kakao.cafe.util.exception.DuplicatedUserException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import com.kakao.cafe.util.exception.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(@Qualifier("jdbcUserRepository") UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(UserRequest userRequest){
        validateDuplicateUser(userRequest);
        User user = User.builder()
                .userId(userRequest.getUserId())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .build();
        userRepository.insert(user);
    }

    private void validateDuplicateUser(UserRequest userRequest) {
        userRepository.selectByUserId(userRequest.getUserId())
                .ifPresent(m -> {
                    throw new DuplicatedUserException("이미 존재하는 회원입니다.");
                });
    }

    @Override
    public List<UserResponse> findUsers(){
        return userRepository.selectAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findUserById(Long id){
        return new UserResponse(userRepository.selectById(id)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용자입니다.")));
    }

    @Override
    public void modifyUser(UserUpdateReqDto userUpdateReqDto) {
        User user = userRepository.selectById(userUpdateReqDto.getId())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용자입니다."));

        validatePassword(userUpdateReqDto.getPassword(), user.getPassword());

        userRepository.update(User.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .password(passwordEncoder.encode(userUpdateReqDto.getNewPassword()))
                .name(userUpdateReqDto.getName())
                .email(userUpdateReqDto.getEmail())
                .build());
    }

    @Override
    public UserResponse login(UserRequest userRequest) {
        User user = userRepository.selectByUserId(userRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용자입니다."));
        validatePassword(userRequest.getPassword(), user.getPassword());

        return new UserResponse(user);
    }

    private void validatePassword(String inputPassword, String dataPassword){
        if(!passwordEncoder.matches(inputPassword, dataPassword)){
            throw new WrongPasswordException("잘못된 비밀번호 입니다.");
        }
    }

}
