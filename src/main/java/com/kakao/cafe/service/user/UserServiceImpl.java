package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserResDto;
import com.kakao.cafe.dto.user.UserUpdateReqDto;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.dto.user.UserReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


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

        //예외처리(비밀번호가 다른 경우)

        userRepository.save(User.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .password(userUpdateReqDto.getNewPassword())
                .name(userUpdateReqDto.getName())
                .email(userUpdateReqDto.getEmail())
                .build());
    }

}
