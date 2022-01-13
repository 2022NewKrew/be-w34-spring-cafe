package com.kakao.cafe.repository;

import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("userRepository")
public class UserRepository {
    private final List<User> users = new ArrayList<>();

//    private final Map<Integer, User> users = new HashMap<>();
//    private int userNumber = 0;

    public void createUser(UserRegistrationDto userDto) {
        User user = new User(userDto.getUserId(), userDto.getPassword(), userDto.getEmail());
        users.add(user);
//        userNumber+=1;
//        users.put(userNumber, user);
    }

    public List<User> readUsers() {
        return users;
//        return new ArrayList<>(users.values());
    }

    public User readUser(String userId) {
//        for (Map.Entry<Integer, User> user : users.entrySet()) {
//            if (user.getValue().getUserId() == userId) {
//                System.out.println(user.getValue());
//                return user.getValue();
//            }
//        }
//        return null;
        return users.stream()
                .filter(user -> Objects.equals(user.getUserId(), userId))
                .findFirst()
                .orElse(null);
    }
}
