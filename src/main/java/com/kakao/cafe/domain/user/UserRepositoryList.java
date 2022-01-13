package com.kakao.cafe.domain.user;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class UserRepositoryList implements UserRepository {
    private List<User> users = new ArrayList<User>();
    private int maxIndex = 0;

    @Override
    public void save(User user){
        try{
            findByStringId(user.getStringId());
            return;
        }
        catch (IllegalArgumentException e){
            user.setId(maxIndex);
            users.add(user);
            maxIndex++;
        }
    }

    @Override
    public void update(int id, User user){
        findById(id).update(user);
    }

    @Override
    public User findById(int id){
        return users.stream().filter(user -> user.getId()==id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public User findByStringId(String stringId){
        return users.stream().filter(user -> user.getStringId().equals(stringId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public List<User> findAll(){
        return users;
    }
}
