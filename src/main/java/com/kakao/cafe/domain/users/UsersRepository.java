package com.kakao.cafe.domain.users;

import com.kakao.cafe.web.dto.UserResponseDto;

import java.sql.*;
import java.util.ArrayList;

public class UsersRepository {
    private final String JDBC_DRIVER;
    private final String DB_URL;
    private Connection connection;

    public UsersRepository() {
        JDBC_DRIVER = "org.h2.driver";
        DB_URL = "jdbc:h2:mem:~/test";

        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "CREATE TABLE user (" +
                    "id BIGINT NOT NULL, " +
                    "name VARCHAR(32), " +
                    "email VARCHAR(32), "+
                    "password VARCHAR(32)," +
                    "created_date VARCHAR(50) " +
                    ")";
            statement.execute(sql);

            save(new UserEntity("test account", "abc@123", "123"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(UserEntity userEntity) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, userEntity.getId());
            ps.setString(2, userEntity.getName());
            ps.setString(3, userEntity.getEmail());
            ps.setString(4, userEntity.getPassword());
            ps.setString(5, userEntity.getTime().toLocalDate().toString());

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
                String dateTime = result.getString("created_date");
                userEntities.add(new UserResponseDto(id, name, email, password, dateTime));
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
            String dateTime = result.getString("created_date");
            UserResponseDto responseDto = new UserResponseDto(id, name, email, password, dateTime);

            return responseDto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserResponseDto findByEmail(String email) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "SELECT * FROM user WHERE email = ?";
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            final ResultSet result = ps.executeQuery();

            result.next();
            Long id = result.getLong("id");
            String name = result.getString("name");
            String password = result.getString("password");
            String dateTime = result.getString("created_date");
            UserResponseDto responseDto = new UserResponseDto(id, name, email, password, dateTime);

            return responseDto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
