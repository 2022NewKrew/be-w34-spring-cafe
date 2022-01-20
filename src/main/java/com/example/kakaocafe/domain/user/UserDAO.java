package com.example.kakaocafe.domain.user;

import com.example.kakaocafe.domain.user.dto.SignUpForm;
import com.example.kakaocafe.domain.user.dto.UpdateUserForm;
import com.example.kakaocafe.domain.user.dto.UserProfile;
import com.example.kakaocafe.domain.user.dto.UserProfileOfTableRow;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public void create(SignUpForm signUpForm) {
        final String sql = "INSERT INTO USER (EMAIL, PASSWORD, NAME) VALUES(?,?,?)";

        final Object[] params = {
                signUpForm.getEmail(),
                signUpForm.getPassword(),
                signUpForm.getName()
        };

        jdbcTemplate.update(sql, params);
    }

    public void update(UpdateUserForm updateUserForm) {
        final String sql = "UPDATE USER SET NAME=? WHERE ID=?";

        jdbcTemplate.update(sql, updateUserForm.getName(), updateUserForm.getId());
    }

    public Optional<UserProfile> getUserProfileById(long id) {
        final String sql = "SELECT EMAIL, NAME, DATE_FORMAT(CREATED, '%y-%m-%d') as `created` FROM USER WHERE ID=?";

        final List<UserProfile> result = jdbcTemplate.query(sql, userProfileMapper(), id);

        return Optional.ofNullable(DataAccessUtils.singleResult(result));
    }

    public Optional<User> findByEmail(String email) {
        final String sql = "SELECT ID, CREATED, EMAIL, PASSWORD, NAME FROM USER WHERE EMAIL = ?";

        final List<User> user = jdbcTemplate.query(sql, userMapper(), email);

        return Optional.ofNullable(DataAccessUtils.singleResult(user));
    }

    public List<UserProfileOfTableRow> getAllUserProfileOfTableRow() {
        final String sql = "SELECT EMAIL, NAME, DATE_FORMAT(CREATED, '%y-%m-%d') as `created` FROM USER";

        return jdbcTemplate.query(sql, userProfileOfTableRowRowMapper());
    }

    public boolean isExistEmail(String email) {
        final String sql = "SELECT EXISTS(SELECT * FROM USER WHERE EMAIL = ?)";

        final Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, email);
        Objects.requireNonNull(result);

        return result;
    }

    private RowMapper<User> userMapper() {
        return (rs, rowNum) -> {
            if (rs.wasNull()) return null;

            return User.builder()
                    .id(rs.getLong("id"))
                    .created(rs.getTimestamp("created").toLocalDateTime())
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .name(rs.getString("name"))
                    .build();
        };
    }

    private RowMapper<UserProfileOfTableRow> userProfileOfTableRowRowMapper() {
        return (rs, rowNum) -> {
            final String email = rs.getString("email");
            final String created = rs.getString("created");
            final String name = rs.getString("name");

            return new UserProfileOfTableRow(email, name, created);
        };
    }

    private RowMapper<UserProfile> userProfileMapper() {
        return (rs, rowNum) -> {
            final String email = rs.getString("email");
            final String name = rs.getString("name");

            return new UserProfile(email, name);
        };
    }
}
