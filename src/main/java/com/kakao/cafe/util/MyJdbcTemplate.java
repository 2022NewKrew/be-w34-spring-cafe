package com.kakao.cafe.util;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MyJdbcTemplate {
    private final DataSource dataSource;

    public <T> List<T> query(String query, RowMapper<T> rowMapper){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return convert(resultSet, rowMapper);
        } catch (SQLException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
    }

    private <T> List<T> convert(ResultSet resultSet, RowMapper<T> rowMapper) throws SQLException {
        if(!resultSet.next()){
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
            throw new IllegalStateException(exception.getMessage());
        }
    }

    private void setObjects(PreparedStatement statement, Object... values) throws SQLException {
        if(values == null){
            return;
        }

        for(int index=0; index < values.length; index++){
            statement.setObject(index+1, values[index]);
        }
    }
}
