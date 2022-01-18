package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserMemoryRepository implements UserRepository{

    List<User> users = new ArrayList<>();

    public void save(User user){
        users.add(user);
    }

    public List<User> findAll(){
        return users;
    }

    public Long getNumberOfUsers(){
        return (long) users.size();
    }

    public Optional<User> findOneByUserId(String userId){
        return Optional.of(users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .collect(Collectors.toList()).get(0));
    }

    public void updateOne(User user){}
}
