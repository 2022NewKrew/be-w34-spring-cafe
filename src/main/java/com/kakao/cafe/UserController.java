package com.kakao.cafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Controller
public class UserController { Logger logger = LoggerFactory.getLogger(UserController.class);

    private final DataSource dataSource;

    public UserController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void insertUser(User user) {
        final String sql = "INSERT INTO USERS(userid, password, name, email) VALUES (?,?,?,?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getEmail());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/user/create")
    public String postUserCreate(String userId, String password, String name, String email) {
        logger.info("[postUserCreate] userId = {}, password = {}, name = {}, email = {}", userId, password, name, email);
        insertUser(new User(userId, password, name, email));
        return "redirect:/users";
    }

    private List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT userid, password, name, email FROM USERS");
            while(resultSet.next()) {
                users.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        logger.info("[getUsers]");
        List<Map<String, String>> userList = new ArrayList<>();
        List<User> users = selectAllUsers();
        for(int i = 0; i < users.size(); i++) {
            userList.add(Map.of("index", Integer.toString(i+1),
                    "userId", users.get(i).getUserId(),
                    "name", users.get(i).getName(),
                    "email", users.get(i).getEmail())
                    );
        }
        model.addAttribute("users", userList);
        return "user/list";
    }

    private User findUserById(String userId) {
        final String sql = "SELECT password, name, email FROM USERS WHERE userid = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                return new User(userId, resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/users/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        logger.info("[getUserProfile] userId = {}", userId);
        User matchUser = findUserById(userId);
        if(matchUser == null) {
            return "user/profile";
        }
        model.addAttribute("name", matchUser.getName());
        model.addAttribute("email", matchUser.getEmail());
        return "user/profile";
    }

    @GetMapping("/user/{id}/form")
    public String updateForm(@PathVariable String id, Model model) {
        logger.info("GET /user/{id}/form: id = {}", id);
        User selectedUser = findUserById(id);
        if(selectedUser == null) {
            return "user/updateForm";
        }
        model.addAttribute("userId", selectedUser.getUserId());
        model.addAttribute("password", selectedUser.getPassword());
        model.addAttribute("name", selectedUser.getName());
        model.addAttribute("email", selectedUser.getEmail());
        return "user/updateForm";
    }

    private void updateUserById(String userId, String password, String name, String email) {
        final String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userid=?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, password);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, userId);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/user/{pathUserId}/update")
    public String updateUserInfo(String userId, String password, String name, String email, HttpSession session) {
        logger.info("POST /user/{}/update UserId = {}, password = {}, name = {}, email = {}", userId, userId, password, name, email);
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            logger.info("Session is null");
            session.setAttribute("errormsg", "세션이 만료되었습니다.");
            return "redirect:/user/error";
        }
        User user = (User)value;
        if(user.getUserId().equals(userId) && user.getPassword().equals(password)) {
            logger.info("Update name and email: name = {}, email = {}", name, email);
            updateUserById(userId, password, name, email);
            return "redirect:/users";
        }
        logger.info("Wrong userId or password");
        session.setAttribute("errormsg", "사용자 아이디 혹은 비밀번호가 일치하지 않습니다.");
        return "redirect:/user/error";
    }

    @PostMapping("/user/login")
    public String login(String userId, String password, HttpSession session) {
        logger.info("POST /user/login: userId = {}, password = {}", userId, password);
        User user = findUserById(userId);
        if(user == null) {
            logger.info("Cannot find userId = {}", userId);
            return "redirect:/user/login";
        }
        if(!user.getPassword().equals(password)) {
            logger.info("Wrong password for userId = {}", userId);
            return "redirect:/user/login";
        }
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("/user/error")
    public String error(HttpSession session) {
        logger.info("GET /user/error");
        return "user/error";
    }
}
