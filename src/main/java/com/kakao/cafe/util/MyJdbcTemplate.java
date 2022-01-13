package com.kakao.cafe.util;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MyJdbcTemplate {
    private static final Logger logger = LoggerFactory.getLogger(MyJdbcTemplate.class);

    private final DataSource dataSource;

    public <T> List<T> query(String query, RowMapper<T> rowMapper){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = preparedStatement.executeQuery();

            return convert(resultSet, rowMapper);
        } catch (SQLException exception) {
            logger.info(exception.getMessage());
            return Collections.emptyList();
        }
    }

    private <T> List<T> convert(ResultSet resultSet, RowMapper<T> rowMapper) throws SQLException {
        if(resultSet.isAfterLast()){
            return Collections.emptyList();
        }

        List<T> results = new ArrayList<>();
        int rowCount = 1;
        while(resultSet.next()){
            results.add(rowMapper.mapRow(resultSet, rowCount++));
        }

        return results;
    }

    public void update(String query, Object... values){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            setObjects(preparedStatement, values);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            logger.info(exception.getMessage());
        }
    }

    private void setObjects(PreparedStatement statement, Object... values) throws SQLException {
        if(values == null){
            return;
        }

        for(int index=0; index < values.length; index++){
            Object value = convertIfNotSupport(values[index]);
            statement.setObject(index+1, value);
        }
    }

    private Object convertIfNotSupport(Object object){
        if(object instanceof LocalDateTime){
            return Timestamp.valueOf((LocalDateTime) object);
        }

        return object;
    }
}
