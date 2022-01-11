package com.kakao.cafe.user;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class User {
    // Simulate in-memory database
    private static final List<User> users = new ArrayList<>();
    private static Long next_id = 0L;
    // Simulate in-memory database


    private final Long id;
    private final String username;
    private final String password;
    private final String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

        // Simulate in-memory database
        // Assumes User() is called only on registration
        id = next_id++;
        // Simulate in-memory database
    }


    // Simulate in-memory database
    public static void addUser(User user) {
        try {
            findUserByUsername(user.username);
            throw new RuntimeException("Username is already in use: " + user.username);
        } catch (NoSuchElementException e) {
            users.add(user);
        }

    }

    public static List<User> getUsers() {
        return users;
    }

    public static User findUserByUsername(String username) {
        return users.stream().filter(user -> user.username.equals(username)).findFirst().orElseThrow(
                () -> new NoSuchElementException("Username not found: " + username));
    }
    // Simulate in-memory database


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\'' + '}';
    }
}
