package com.kakao.cafe.domain.user.repositoryimpl;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("UserRepositoryList")
public class UserRepositoryList implements UserRepository {
    private List<User> users = new ArrayList<User>();
    private int maxIndex = 1;

    @Override
    public User save(User user){
        if(user.isNew()) {
            insert(user);
            return user;
        }
        update(user);
        return user;
    }

    @Override
    public User findById(int id){
        return users.stream().filter(user -> user.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByStringId(String stringId){
        return users.stream().filter(user -> user.getStringId().equals(stringId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll(){
        return users;
    }

    private void insert(User user){
        users.add(User.builder()
                .id(maxIndex)
                .stringId(user.getStringId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .build());
        maxIndex++;
    }

    private void update(User user){
        User userToUpdate = findById(user.getId());
        userToUpdate.changeName(user.getName());
        userToUpdate.changePassword(user.getPassword());
        userToUpdate.changeEmail(user.getEmail());
    }
}
