package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.LoginException;
import com.kakao.cafe.util.ErrorMessage;
import com.kakao.cafe.util.JdbcUtils;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JdbcUserRepository implements RepositoryInterface<User> {
    private final DataSource dataSource;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public JdbcUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User save(User user) {
        log.info(user.toString());
        String sql = "insert into users(name, password, email) values(?, ?, ?)";
        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setUserId(resultSet.getLong(1));
                return user;
            }
            throw new SQLException("userId 조회 실패");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }

    }

    @Override
    public Optional<User> findById(Long userId) {
        String sql = "select * from users where userid = ?";

        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(getResult(resultSet));
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        String sql = "select * from users where email = ?";

        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = getResult(resultSet);
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from users";
        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = getResult(resultSet);
                users.add(user);
            }

            return users;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public User update(User user) {
        String sql = "update users set name = ?, password = ?, email = ? where userid = ?";
        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setLong(4, user.getUserId());

            preparedStatement.executeUpdate();
            return user;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from users where userid = ?";
        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    private User getResult(ResultSet rs) {
        try {
            User user = new User();
            user.setUserId(rs.getLong("userid"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            return user;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
