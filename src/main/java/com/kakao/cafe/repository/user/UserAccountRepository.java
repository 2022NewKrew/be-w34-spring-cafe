package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.domain.UserAccountDTO;
import com.kakao.cafe.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UserAccountRepository implements Repository<UserAccount, UserAccountDTO, String> {
    private final JdbcTemplate jdbcTemplate;


    public UserAccountRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserAccount save(UserAccountDTO userAccountDTO) {
        return null;
    }

    @Override
    public Optional<UserAccount> findByUserId(String userId) {
        return Optional.empty();
    }

    @Override
    public List<UserAccount> findAll() {
        return null;
    }

}
