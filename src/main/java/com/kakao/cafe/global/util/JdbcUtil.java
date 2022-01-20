package com.kakao.cafe.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcUtil<T> {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<T> rowMapper;

    public Optional<T> readRecord(String sql, Object... params) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, params));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<T> readRecords(String sql, Object... params) {
        return jdbcTemplate.query(sql, rowMapper, params);
    }

    public void writeRecord(String sql, Object... params) {
        jdbcTemplate.update(sql, params);

    }
}
