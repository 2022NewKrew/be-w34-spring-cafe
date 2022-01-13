package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.ShowUserDto;
import com.kakao.cafe.repository.UserDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDAOInterface userDAO;

    @Autowired
    public UserService(UserDAOInterface userDAO) {
        this.userDAO = userDAO;
    }

    public void save(CreateUserDto createUserDto) {
        User user = new User(createUserDto);
        userDAO.save(user);
    }

    public List<ShowUserDto> findAll() {
        return userDAO.findAll()
                .stream()
                .map(ShowUserDto::new)
                .collect(Collectors.toList());
    }

    public ShowUserDto findById(String userId) {
        return new ShowUserDto(userDAO.findById(userId));
    }
}
