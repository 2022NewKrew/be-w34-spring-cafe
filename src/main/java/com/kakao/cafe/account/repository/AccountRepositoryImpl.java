package com.kakao.cafe.account.repository;

import com.kakao.cafe.account.entity.Account;
import com.kakao.cafe.exception.custom.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final JdbcTemplate jdbcTemplate;

    public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account save(Account account) {
        jdbcTemplate.update("INSERT INTO account(user_id, name, password, email) VALUES(?, ?, ?, ?)"
                , account.getUserId(), account.getName(), account.getPassword(), account.getEmail());
        return account;
    }

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("SELECT user_id, name, password, email FROM account", accountRowMapper());
    }

    @Override
    public Account findById(String userId) {
        List<Account> query = jdbcTemplate.query("SELECT user_id, name, password, email FROM account WHERE user_id = ?", accountRowMapper(), userId);
        if(query.isEmpty()) throw new NotFoundException();
        return query.get(0);
    }

    private RowMapper<Account> accountRowMapper() {
        return (rs, rowNum) -> {
            return Account.builder()
                    .userId(rs.getString("user_id"))
                    .name(rs.getString("name"))
                    .password(rs.getString("password"))
                    .email(rs.getString("email"))
                    .build();
        };
    }
}
