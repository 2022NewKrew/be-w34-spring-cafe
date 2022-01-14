package com.kakao.cafe.domain.users;

import com.kakao.cafe.web.dto.UserResponseDto;
import com.kakao.cafe.web.dto.UsersCreateRequestDto;

import java.sql.*;
import java.util.ArrayList;

public class UsersRepository {
    private final String JDBC_DRIVER;
    private final String DB_URL;
    private Connection connection;

    public UsersRepository() {
        JDBC_DRIVER = "org.h2.driver";
        DB_URL = "jdbc:h2:mem:test";

        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "CREATE TABLE user (" +
                    "id BIGINT NOT NULL, " +
                    "name VARCHAR(32), " +
                    "email VARCHAR(32), "+
                    "password VARCHAR(32)" +
                    ")";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(UserEntity userEntity) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "INSERT INTO user VALUES (?, ?, ?, ?)";
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, userEntity.getId());
            ps.setString(2, userEntity.getName());
            ps.setString(3, userEntity.getEmail());
            ps.setString(4, userEntity.getPassword());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserResponseDto> findAll() {
        ArrayList<UserResponseDto> userEntities = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "SELECT * FROM user";
            final PreparedStatement ps = connection.prepareStatement(sql);
            final ResultSet result =  ps.executeQuery();

            while (result.next()) {
                Long id = result.getLong("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("password");
                userEntities.add(new UserResponseDto(id, name, email, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userEntities;
    }

    public UserResponseDto findById(Long id) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "SELECT * FROM user WHERE id = " + id.toString();
            final PreparedStatement ps = connection.prepareStatement(sql);
            final ResultSet result = ps.executeQuery();

            result.next();
            String name = result.getString("name");
            String email = result.getString("email");
            String password = result.getString("password");
            UserResponseDto responseDto = new UserResponseDto(id, name, email, password);

            return responseDto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Long id, UserEntity userEntity) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "UPDATE user SET name=?, password=? WHERE id=?";
            final PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, userEntity.getName());
            ps.setString(2, userEntity.getPassword());
            ps.setLong(3, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
