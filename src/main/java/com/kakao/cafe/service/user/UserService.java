package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.Entity.User;
import com.kakao.cafe.domain.Repository.user.UserRepository;
import com.kakao.cafe.dto.user.SignUpDto;
import com.kakao.cafe.dto.user.UserInfoDto;
import com.kakao.cafe.exceptions.NoSuchUserException;
import com.kakao.cafe.exceptions.PasswordMismatchException;
import com.kakao.cafe.exceptions.UserIdDuplicationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public void saveNewUser(SignUpDto signUpDto) throws UserIdDuplicationException {
        if (this.userRepository.isUserIdExist(signUpDto.getUserId())) {
            throw new UserIdDuplicationException();
        }
        userRepository.saveUser(signUpDto.toEntity());
    }

    // Repo로부터 회원 리스트를 받고, 컨트롤러에 전달해줄 요소들만 포함한 DTO로 재구성하여 리턴
    public List<UserInfoDto> getUserList() {
        List<UserInfoDto> userList = this.userRepository.findAllUsers().stream()
                .map(user -> new UserInfoDto(user.getUserId(), user.getName(), user.getEmail())).collect(Collectors.toList());
        return userList;
    }

    public UserInfoDto getUserByUserId(String userId) throws NoSuchUserException {
        User user = this.userRepository.findUserByUserId(userId);
        return new UserInfoDto(user.getUserId(), user.getName(), user.getEmail());
    }

    public void updateUser(SignUpDto signUpDto) throws NoSuchUserException, PasswordMismatchException {
        User targetUser = this.userRepository.findUserByUserId(signUpDto.getUserId());
        if (!targetUser.getPassword().equals(signUpDto.getPassword())) {
            throw new PasswordMismatchException();
        }
        this.userRepository.updateUser(signUpDto.toEntity());
    }

}
