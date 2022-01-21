package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Qna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            jdbcTemplate.queryForObject("SELECT id FROM QNA WHERE id = ?", Integer.class, qna.getId());
            jdbcTemplate.update("UPDATE QNA " +
                    "SET writer = ?, title = ?, contents = ?, deleted = ? " +
                    "WHERE id = ?", qna.getWriter(), qna.getTitle(), qna.getContents(), qna.getDeleted(), qna.getId());
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            jdbcTemplate.update("INSERT INTO QNA(writer, title, contents, deleted, created_at) " +
                    "VALUES(?, ? ,?, ?, ?)", qna.getWriter(), qna.getTitle(), qna.getContents(), qna.getDeleted(), qna.getCreatedAt());
        }
    }

    @Override
    public List<Qna> findAllByDeleted(Boolean deleted) {
        return jdbcTemplate.query("SELECT id, writer, title, contents, created_at FROM QNA WHERE deleted = ?", this::qnaMapRow, deleted);
    }

    public Page<Qna> findAllByDeleted(Boolean deleted, Pageable page) {
        Sort.Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Sort.Order.by("created_at");

        List<Qna> qnaList = jdbcTemplate.query("SELECT id, writer, title, contents, created_at FROM QNA ORDER BY " + order.getProperty() + " "
                        + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset(),
                this::qnaMapRow);

        return new PageImpl<>(qnaList, page, count());
    }

    private int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM QNA", Integer.class);
    }

    @Override
    public Optional<Qna> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT id, writer, title, contents, created_at FROM QNA WHERE id = ?", this::qnaMapRow, id));
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
    }

    private Qna qnaMapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Qna(resultSet.getInt("id"), resultSet.getString("writer"),
                resultSet.getString("title"), resultSet.getString("contents"),
                resultSet.getTimestamp("created_at").toLocalDateTime());
    }
}

