package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.Entity.User;
import com.kakao.cafe.domain.Repository.user.UserRepository;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.exceptions.UserNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public void saveNewUser(UserDto userDto) {
        userRepository.saveUser(userDto.toEntity());
    }

    // Repo로부터 회원 리스트를 받고, 컨트롤러에 전달해줄 요소들만 포함한 DTO로 재구성하여 리턴
    public List<UserDto> getUserList() {
        List<UserDto> userList = new ArrayList<>();
        for (User user : this.userRepository.findAllUsers()) {
            UserDto userDTO = new UserDto(user.getUserId(), user.getName(), user.getEmail());
            userList.add(userDTO);
        }
        return userList;
    }

    public UserDto getUserByUserId(String userId) {
        try {
            User user = this.userRepository.findUserByUserId(userId);
            UserDto userDto = new UserDto(user.getUserId(), user.getName(), user.getEmail());
            return userDto;
        } catch (UserNotExistException e) {
            return new UserDto(null, null, null);
        }
    }



}
