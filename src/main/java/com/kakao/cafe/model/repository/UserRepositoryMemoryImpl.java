package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.User;
import com.kakao.cafe.model.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryMemoryImpl implements UserRepository {
    private static final Map<Long, User> storedUsers = new HashMap<>();
    private static long maxID = 0L;

    private Optional<User> findUserInStoredUsers(long id) {
        return Optional.ofNullable(storedUsers.get(id));
    }

    private Optional<User> findUserInStoredUsers(String userId) {
        return storedUsers.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }

    private Optional<User> findUserInStoredUsers(String userId, String password) {
        return storedUsers.values().stream()
                .filter(user -> user.getUserId().equals(userId) && user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public boolean insertUser(UserDTO userDTO) {
        if (findUserInStoredUsers(userDTO.getUserId()).isPresent()) {
            return false;
        }

        User newUser = new User(maxID++, userDTO);
        storedUsers.put(newUser.getId(), newUser);
        return false;
    }

    @Override
    public List<User> selectAllUsers() {
       return new ArrayList<>(storedUsers.values());
    }

    @Override
    public Optional<User> selectUserById(Long id) {
        return findUserInStoredUsers(id);
    }

    @Override
    public Optional<User> selectUserByUserId(String userId) {
        return findUserInStoredUsers(userId);
    }

    @Override
    public Optional<User> selectUserByLoginInfo(String userId, String password) {
        return findUserInStoredUsers(userId, password);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        Optional<User> foundUser = findUserInStoredUsers(userDTO.getUserId());

        if (foundUser.isEmpty()) {
            return false;
        }

        User newUser = new User(foundUser.get().getId(), userDTO);
        storedUsers.put(newUser.getId(), newUser);
        return true;
    }

    @Override
    public boolean deleteUser(String userId, String password) {
        Optional<User> foundUser = findUserInStoredUsers(userId, password);

        if (foundUser.isEmpty()) {
            return false;
        }

        storedUsers.remove(foundUser.get().getId());
        return true;
    }
}
