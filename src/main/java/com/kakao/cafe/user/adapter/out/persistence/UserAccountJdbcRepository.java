package com.kakao.cafe.user.adapter.out.persistence;

<<<<<<< HEAD:src/main/java/com/kakao/cafe/user/adapter/out/persistence/UserAccountJdbcRepository.java
import com.kakao.cafe.user.domain.UserAccount;
=======
import com.kakao.cafe.domain.post.QuestionPost;
import com.kakao.cafe.domain.user.UserAccount;
import com.kakao.cafe.domain.user.UserAccountRepository;
>>>>>>> 097c71c (feat : update 쿼리 작성):src/main/java/com/kakao/cafe/persistence/user/UserAccountJdbcRepository.java
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("jdbc-account-db")
@RequiredArgsConstructor
public class UserAccountJdbcRepository implements UserAccountRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserAccount save(UserAccount userAccount) {
        String sql = "insert into user_account (username, password, email, created_at) values(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"user_account_id"});
            ps.setString(1, userAccount.getUsername());
            ps.setString(2, userAccount.getPassword());
            ps.setString(3, userAccount.getEmail());
            ps.setTimestamp(4, Timestamp.valueOf(userAccount.getCreatedAt()));
            return ps;
        }, keyHolder);

        return UserAccount.builder()
                .userAccountId(keyHolder.getKey().longValue())
                .username(userAccount.getUsername())
                .password(userAccount.getPassword())
                .createdAt(userAccount.getCreatedAt())
                .email(userAccount.getEmail())
                .build();
    }

    @Override
    public Optional<UserAccount> findById(Long id) {
        String sql = "select user_account_id, username, password, email, created_at from user_account where user_account_id = ?";

        try {
            UserAccount userAccount = jdbcTemplate.queryForObject(
                    sql,
                    (result, row) -> UserAccount.builder()
                            .userAccountId(result.getLong("user_account_id"))
                            .username(result.getString("username"))
                            .password(result.getString("password"))
                            .email(result.getString("email"))
                            .createdAt(result.getTimestamp("created_at").toLocalDateTime())
                            .build(),
                    id);

            return Optional.of(userAccount);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserAccount> findByEmail(String email) {
        String sql = "select user_account_id, username, password, email, created_at from user_account where email = ?";

        try {
            UserAccount userAccount = jdbcTemplate.queryForObject(
                    sql,
                    (result, row) -> UserAccount.builder()
                                .userAccountId(result.getLong("user_account_id"))
                                .username(result.getString("username"))
                                .password(result.getString("password"))
                                .email(result.getString("email"))
                                .createdAt(result.getTimestamp("created_at").toLocalDateTime())
                                .build(),
                    email);

            return Optional.of(userAccount);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public List<UserAccount> findAll() {
        String sql = "select user_account_id, username, password, email, created_at from user_account";

        List<UserAccount> userAccounts = jdbcTemplate.query(
                sql,
                (result, row) -> UserAccount.builder()
                        .userAccountId(result.getLong("user_account_id"))
                        .username(result.getString("username"))
                        .password(result.getString("password"))
                        .email(result.getString("email"))
                        .createdAt(result.getTimestamp("created_at").toLocalDateTime())
                        .build());
        return userAccounts;
    }

    @Override
    public void update(UserAccount userAccount) {
        String sql = "update user_account set username = ?, password = ?, where user_account_id = ?";

        jdbcTemplate.update(sql, userAccount.getUsername(), userAccount.getPassword(), userAccount.getUserAccountId());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from user_account where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAll() {

    }
}
