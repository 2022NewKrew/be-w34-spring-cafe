package com.kakao.cafe.common;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public interface BaseEntityRowMapper<T extends BaseEntity> extends RowMapper<T> {
    default void mapBaseProperties(T target, ResultSet rs) throws SQLException {
        target.setId(rs.getLong("ID"));
        target.setCreatedAt(rs.getObject("CREATED_AT", OffsetDateTime.class));
        target.setUpdatedAt(rs.getObject("UPDATED_AT", OffsetDateTime.class));
    }
}
