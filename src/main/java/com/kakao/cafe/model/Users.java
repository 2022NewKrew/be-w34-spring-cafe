package com.kakao.cafe.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Users {
    private final Map<String, User> users = new HashMap<>();

    public void addUser(User user){
        validateDuplicateUserId(user.getUserId());

        users.put(user.getUserId(), user);
    }

    public List<User> getAllUsers(){
        return users.values().stream().collect(Collectors.toUnmodifiableList());
    }

    public User findUserById(String userId){
        return Optional.ofNullable(users.get(userId)).orElseThrow(IllegalArgumentException::new);
    }

    private void validateDuplicateUserId(String userId){
        if(users.containsKey(userId)){
            throw new IllegalArgumentException();
        }
    }

}
