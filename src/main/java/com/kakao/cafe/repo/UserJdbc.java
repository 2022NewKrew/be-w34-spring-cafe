package com.kakao.cafe.repo;

import com.kakao.cafe.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class UserJdbc implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    UserJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(Objects.requireNonNull(dataSource));
    }

    @Override
    public boolean add(@NonNull final User user) {
        final int result = jdbcTemplate.update(con -> {
            final PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO userlist (id, password, name, email) " +
                            "values (?,?,?,?)"
            );

            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            return pstmt;
        });

        return (result > 0);
    }

    @Override
    public boolean checkIdExist(@NonNull final String id) {
        final List<String> list = jdbcTemplate.query(
                con -> {
                    final PreparedStatement pstmt = con.prepareStatement(
                            "SELECT id FROM userlist WHERE id = ? LIMIT 1"
                    );
                    pstmt.setString(1, id);
                    return pstmt;
                },
                (rs, count) -> rs.getString("id")
        );

        return (list.size() > 0);
    }

    @Override
    public User findById(@NonNull final String id) {
        final List<User> list = jdbcTemplate.query(
                con -> {
                    final PreparedStatement pstmt = con.prepareStatement(
                            "SELECT * FROM userlist WHERE id = ? LIMIT 1"
                    );
                    pstmt.setString(1, id);
                    return pstmt;
                },
                (rs, count) -> new User(rs)
        );

        if (list.size() == 0) {
            return User.NONE;
        }

        return list.get(0);
    }

    @Override
    public List<User> getList() {
        return Collections.unmodifiableList(
                jdbcTemplate.query(
                        con -> con.prepareStatement(
                                "SELECT * FROM userlist"
                        ),
                        (rs, count) -> new User(rs)
                )
        );
    }
}
