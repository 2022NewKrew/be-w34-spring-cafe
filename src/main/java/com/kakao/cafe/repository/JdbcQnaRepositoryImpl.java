package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Qna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcQnaRepositoryImpl implements QnaRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcQnaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Qna qna) {
        try {
            jdbcTemplate.queryForObject("SELECT index FROM QNA WHERE index = ?", Integer.class, qna.getIndex());
            jdbcTemplate.update("UPDATE QNA " +
                    "SET writer = ?, titile = ?, contents = ?" +
                    "WHERE index = ?", qna.getWriter(), qna.getTitle(), qna.getContents(), qna.getIndex());
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            jdbcTemplate.update("INSERT INTO QNA(writer, title, contents)" +
                    "VALUES(?, ? ,?)", qna.getWriter(), qna.getTitle(), qna.getContents());
        }
    }

    @Override
    public List<Qna> findAll() {
        return jdbcTemplate.query("SELECT index, writer, title, contents FROM QNA", this::qnaMapRow);
    }

    @Override
    public Optional<Qna> findByIndex(Integer index) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT index, writer, title, contents FROM QNA WHERE index = ?", this::qnaMapRow, index));
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
    }

    private Qna qnaMapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Qna(resultSet.getInt("index"), resultSet.getString("writer"),
                resultSet.getString("title"), resultSet.getString("contents"));
    }
}

