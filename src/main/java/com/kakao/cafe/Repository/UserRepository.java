package com.kakao.cafe.Repository;

import com.kakao.cafe.model.User.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final List<User> userList = new CopyOnWriteArrayList<>();
    private final AtomicLong sequenceId = new AtomicLong();

    public List<User> getUserList() {
        return userList;
    }

    public void add(User user) {
        user.setId(sequenceId.incrementAndGet());
        this.userList.add(user);
    }
}
