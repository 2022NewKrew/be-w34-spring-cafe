package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Qna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcQnaRepositoryImpl implements QnaRepository {

    private final DataSource dataSource;

    @Autowired
    public JdbcQnaRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Qna qna) {
        PreparedStatement statement = null;
        String query = "";
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            query = "SELECT index FROM QNA WHERE index = " + qna.getIndex();
            statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            query = "INSERT INTO QNA(writer, title, contents) " +
                    "VALUES ('" + qna.getWriter() + "', '" + qna.getTitle() + "', '" + qna.getContents() + "');";

            if (resultSet.next()) {
                query = "UPDATE USER_PROFILE " +
                        "SET writer='" + qna.getWriter() + "', title='" + qna.getTitle() + "', contents='" + qna.getContents()
                        + "' WHERE index=" + qna.getIndex();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement = connection.prepareStatement(query);
            statement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Qna> findAll() {
        List<Qna> qnaList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();

            String query = "SELECT index, writer, title, contents FROM QNA";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Qna qna = new Qna(resultSet.getInt("index"), resultSet.getString("writer"),
                        resultSet.getString("title"), resultSet.getString("contents"));
                qnaList.add(qna);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return qnaList;
    }

    @Override
    public Optional<Qna> findByIndex(Integer index) {
        Optional<Qna> qna = Optional.empty();
        try {
            Connection connection = dataSource.getConnection();

            String query = "SELECT index, writer, title, contents FROM QNA WHERE index = " + index;
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Qna resultQna = new Qna(resultSet.getInt("index"), resultSet.getString("writer"),
                        resultSet.getString("title"), resultSet.getString("contents"));
                qna = Optional.of(resultQna);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return qna;
    }
}

