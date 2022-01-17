package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserResDto;
import com.kakao.cafe.dto.user.UserUpdateReqDto;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.dto.user.UserReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("jdbcUserRepository") UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(UserReqDto userReqDto){
        validateDuplicateUser(userReqDto);
        User user = User.builder()
                .userId(userReqDto.getUserId())
                .password(userReqDto.getPassword())
                .email(userReqDto.getEmail())
                .name(userReqDto.getName())
                .build();
        userRepository.save(user);
    }

    private void validateDuplicateUser(UserReqDto userReqDto) {
        userRepository.findByUserId(userReqDto.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    @Override
    public List<UserResDto> findUsers(){
        return userRepository.findAll().stream()
                .map(UserResDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserResDto findUserById(Long id){
        return new UserResDto(userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("존재하지 않는 사용자입니다.")));
    }

    @Override
    public void updateUser(UserUpdateReqDto userUpdateReqDto) {
        User user = userRepository.findById(userUpdateReqDto.getId())
                .orElseThrow(() -> new NullPointerException("존재하지 않는 사용자입니다."));

        //예외처리예정(비밀번호가 다른 경우)
        validatePassword(userUpdateReqDto.getPassword(), user.getPassword());

        userRepository.update(User.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .password(userUpdateReqDto.getNewPassword())
                .name(userUpdateReqDto.getName())
                .email(userUpdateReqDto.getEmail())
                .build());
    }

    @Override
    public UserResDto login(UserReqDto userReqDto) {
        User user = userRepository.findByUserId(userReqDto.getUserId())
                .orElseThrow(() -> new NullPointerException("존재하지 않는 사용자입니다."));
        validatePassword(userReqDto.getPassword(), user.getPassword());

        return new UserResDto(user);
    }

    private void validatePassword(String inputPassword, String dataPassword){
        if(!inputPassword.equals(dataPassword)){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
    }

}
