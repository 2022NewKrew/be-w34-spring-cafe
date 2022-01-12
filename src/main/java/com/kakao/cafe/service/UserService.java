package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.InMemoryUserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

    public void register(User user) {
        inMemoryUserRepository.save(user);
    }

    public List<User> getUserList() {
        return inMemoryUserRepository.findAll();
    }

    public User getUserById(String userId) {
        return inMemoryUserRepository.findByUserId(userId);
    }
}
