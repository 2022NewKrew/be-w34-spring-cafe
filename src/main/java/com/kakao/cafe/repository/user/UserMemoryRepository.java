package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.dto.user.UserCreateCommand;
import com.kakao.cafe.domain.dto.user.UserListShow;
import com.kakao.cafe.domain.dto.user.UserModifyCommand;
import com.kakao.cafe.domain.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserMemoryRepository implements UserRepository{
    private final List<User> repository;

    public UserMemoryRepository() {
        this.repository = new ArrayList<>();
    }

    @Override
    public void store(UserCreateCommand ucc) {
        User user = new User(nextIndex(), ucc.getUserId(), ucc.getPassword(), ucc.getName(), ucc.getEmail());
        this.repository.add(user);
    }

    @Override
    public void modify(String userId, UserModifyCommand umc) {
        User user = new User(findByName(userId), userId, umc.getPassword(), umc.getName(), umc.getEmail());
        repository.set(findByName(user.getUserId()), user);
    }

    @Override
    public void delete(String userId) {
        this.repository.remove(findByName(userId));
    }

    @Override
    public User search(String userId) {
        return this.repository.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public List<UserListShow> getAllUser() {
        return this.repository.stream()
                .map(UserListShow::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private int findByName(String name) {
        User target = search(name);
        return this.repository.indexOf(target);
    }

    private Long nextIndex() {
        return this.repository.get(this.repository.size() - 1).getUserIndex() + 1L;
    }
}
