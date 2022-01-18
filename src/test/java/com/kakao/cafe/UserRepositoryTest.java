package com.kakao.cafe;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {


    }
}
