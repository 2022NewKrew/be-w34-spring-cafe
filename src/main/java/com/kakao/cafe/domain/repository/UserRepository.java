package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements BaseRepository<User> {
    private final List<User> repository;

    public UserRepository() {
        this.repository = new ArrayList<>();
    }

    @Override
    public void store(User user) {
        this.repository.add(user);
    }

    @Override
    public User retrieve(int id) {
        return this.repository.get(id);
    }

    @Override
    public void modify(int id, User user) {
        repository.set(id, user);
    }

    @Override
    public User delete(int id) {
        return this.repository.remove(id);
    }

    public User search(String name) {
        return this.repository.stream()
                .filter(user -> user.getUserId().equals(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public int indexOf(String name) {
        User target = search(name);
        return this.repository.indexOf(target);
    }

    public List<User> toList() {
        return this.repository;
    }
}
