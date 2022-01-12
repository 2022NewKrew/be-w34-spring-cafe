package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcUserRepositoryImpl implements UserRepository {

    private final DataSource dataSource;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        PreparedStatement statement = null;
        String query = "";
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            query = "SELECT id FROM USER_PROFILE WHERE user_id = '" + user.getUserId() + "'";
            statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            query = "INSERT INTO USER_PROFILE(user_id, password, name, email) " +
                    "VALUES ('" + user.getUserId() + "', '" + user.getPassword() + "', '" + user.getName() + "', '" + user.getEmail() + "');";

            if (resultSet.next()) {
                query = "UPDATE USER_PROFILE " +
                        "SET password='" + user.getPassword() + "', name='" + user.getName() + "', email='" + user.getEmail()
                        + "' WHERE user_id='" + user.getUserId() + "'";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement = connection.prepareStatement(query);
            statement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();

            String query = "SELECT user_id, password, name, email FROM USER_PROFILE";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getString("user_id"), resultSet.getString("password")
                        , resultSet.getString("name"), resultSet.getString("email"));

                userList.add(user);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        Optional<User> user = Optional.empty();
        try {
            Connection connection = dataSource.getConnection();

            String query = "SELECT user_id, password, name, email FROM USER_PROFILE WHERE user_id = '" + userId + "'";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = Optional.of(new User(resultSet.getString("user_id"), resultSet.getString("password")
                        , resultSet.getString("name"), resultSet.getString("email")));

            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
