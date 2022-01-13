package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.domain.UserAccountDTO;
import com.kakao.cafe.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserAccountRepository implements Repository<UserAccount, UserAccountDTO, String> {
    private final JdbcTemplate jdbcTemplate;

    public UserAccountRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserAccount save(UserAccountDTO userAccountDTO) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user_account").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", userAccountDTO.getUserId());
        parameters.put("password", userAccountDTO.getPassword());
        parameters.put("user_name", userAccountDTO.getName());
        parameters.put("email", userAccountDTO.getEmail());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        userAccountDTO.setId(key.intValue());

        return new UserAccount(userAccountDTO);
    }

    @Override
    public Optional<UserAccount> findById(String userId) {
        List<UserAccount> result = jdbcTemplate.query("select * from user_account where user_id = ?", userAccountRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public List<UserAccount> findAll() {
        return jdbcTemplate.query("select * from user_account", userAccountRowMapper());
    }

    private RowMapper<UserAccount> userAccountRowMapper(){
        return (rs, rowNum) -> {
            UserAccountDTO userAccountDTO = new UserAccountDTO();
            userAccountDTO.setId(rs.getInt("id"));
            userAccountDTO.setUserId(rs.getString("user_id"));
            userAccountDTO.setPassword(rs.getString("password"));
            userAccountDTO.setName(rs.getString("user_name"));
            userAccountDTO.setEmail(rs.getString("email"));

            return new UserAccount(userAccountDTO);
        };
    }

}
