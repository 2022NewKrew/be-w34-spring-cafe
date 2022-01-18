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
import static com.kakao.cafe.repository.user.UserAccountRepositoryQueryAndNameSpace.ColumnName.*;

public class UserAccountRepository implements Repository<UserAccount, UserAccountDTO, String> {
    private final JdbcTemplate jdbcTemplate;
    private final UserAccountRepositoryQueryAndNameSpace queryAndNameSpace;

    public UserAccountRepository(DataSource dataSource, UserAccountRepositoryQueryAndNameSpace queryAndNameSpace) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.queryAndNameSpace = queryAndNameSpace;
    }

    @Override
    public UserAccount save(UserAccountDTO userAccountDTO) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(queryAndNameSpace.getTableName()).usingGeneratedKeyColumns(ID.getColumnName());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(USER_ID.getColumnName(), userAccountDTO.getUserId());
        parameters.put(PASSWORD.getColumnName(), userAccountDTO.getPassword());
        parameters.put(USER_NAME.getColumnName(), userAccountDTO.getName());
        parameters.put(EMAIL.getColumnName(), userAccountDTO.getEmail());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        userAccountDTO.setId(key.intValue());

        return new UserAccount(userAccountDTO);
    }

    @Override
    public Optional<UserAccount> findById(String userId) {
        List<UserAccount> result = jdbcTemplate.query(queryAndNameSpace.getFindByIdSqlQuery(), userAccountRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public List<UserAccount> findAll() {
        return jdbcTemplate.query(queryAndNameSpace.getFindAllSqlQuery(), userAccountRowMapper());
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(UserAccountDTO userAccountDTO) {
        jdbcTemplate.update(queryAndNameSpace.getUpdateSqlQuery(), userAccountDTO.getPassword(), userAccountDTO.getName(),
                userAccountDTO.getEmail(), userAccountDTO.getUserId());
    }

    private RowMapper<UserAccount> userAccountRowMapper(){
        return (rs, rowNum) -> {
            UserAccountDTO userAccountDTO = new UserAccountDTO();
            userAccountDTO.setId(rs.getInt(ID.getColumnName()));
            userAccountDTO.setUserId(rs.getString(USER_ID.getColumnName()));
            userAccountDTO.setPassword(rs.getString(PASSWORD.getColumnName()));
            userAccountDTO.setName(rs.getString(USER_NAME.getColumnName()));
            userAccountDTO.setEmail(rs.getString(EMAIL.getColumnName()));

            return new UserAccount(userAccountDTO);
        };
    }

}
