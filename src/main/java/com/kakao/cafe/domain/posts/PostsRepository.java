package com.kakao.cafe.domain.posts;

import com.kakao.cafe.web.dto.PostResponseDto;

import java.sql.*;
import java.util.ArrayList;

public class PostsRepository {

    private final String JDBC_DRIVER;
    private final String DB_URL;
    private Connection connection;

    public PostsRepository() {
        JDBC_DRIVER = "org.h2.driver";
        DB_URL = "jdbc:h2:mem:test";

        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "CREATE TABLE post (" +
                    "id BIGINT NOT NULL, " +
                    "writer VARCHAR(32), " +
                    "title VARCHAR(32), "+
                    "content VARCHAR(100), " +
                    "created_date VARCHAR(50) " +
                    ")";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(PostEntity postEntity) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "INSERT INTO post VALUES (?, ?, ?, ?, ?)";
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, postEntity.getId());
            ps.setString(2, postEntity.getWriter());
            ps.setString(3, postEntity.getTitle());
            ps.setString(4, postEntity.getContent());
            ps.setString(5, postEntity.getTime().toString());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PostResponseDto> findAll() {
        ArrayList<PostResponseDto> postsList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "SELECT * FROM post";
            final PreparedStatement ps = connection.prepareStatement(sql);
            final ResultSet result =  ps.executeQuery();

            while (result.next()) {
                Long id = result.getLong("id");
                String writer = result.getString("writer");
                String title = result.getString("title");
                String content = result.getString("content");
                String time = result.getString("created_date");
                String date = time.split("T")[0];
                postsList.add(new PostResponseDto(writer, title, content, 0, id, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postsList;
    }

    public PostResponseDto findById(Long id) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "SELECT * FROM post WHERE id = " + id.toString();
            final PreparedStatement ps = connection.prepareStatement(sql);
            final ResultSet result =  ps.executeQuery();
            result.next();
            String writer = result.getString("writer");
            String title = result.getString("title");
            String content = result.getString("content");
            String time = result.getString("created_date");
            time = time.replace("T", " ");
            PostResponseDto resultDto = new PostResponseDto(writer, title, content, 0, id, time);
            return resultDto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Long id, PostEntity postEntity) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "UPDATE post SET title=?, content=? WHERE id=?";
            final PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, postEntity.getTitle());
            ps.setString(2, postEntity.getContent());
            ps.setLong(3, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
