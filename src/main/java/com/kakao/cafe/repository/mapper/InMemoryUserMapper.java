package com.kakao.cafe.repository.mapper;

import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class InMemoryUserMapper {
    public User mapResult(User result) {
        if (result == null) {
            return null;
        }

        return new User(result.getId(), result.getUserName(), result.getPassword(), result.getName(), result.getEmail());
    }
}
