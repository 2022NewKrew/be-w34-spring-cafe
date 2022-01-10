package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.users.MemoryUsersRepository;
import com.kakao.cafe.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public UsersService(MemoryUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Long signUp(User user) {
        Long savedUserId = usersRepository.save(user);
        return savedUserId;
    }

    public List<User> getUsersAll() {
        return usersRepository.findAll();
    }

    public User getUser(String userId) {
        return usersRepository.findByUserId(userId);
    }

}
