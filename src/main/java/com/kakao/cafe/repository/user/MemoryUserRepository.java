package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("memoryUserRepository")
public class MemoryUserRepository implements UserRepository {

    private List<User> userList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Long insertUser(User user) {
        user.updateId(Long.valueOf(userList.size() + 1));
        userList.add(user);
        return Long.valueOf(userList.size());
    }

    @Override
    public List<User> findAll() {
        List<User> findUsers = new ArrayList<>();
        findUsers.addAll(this.userList);
        return findUsers;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userList.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userList.stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst();
    }

    @Override
    public Long updateUser(User updateUser) {
        int userIndex = (int)(updateUser.getId() - 1);
        userList.set(userIndex, updateUser);
        return updateUser.getId();
    }
}
